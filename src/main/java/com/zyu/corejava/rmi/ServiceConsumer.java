package com.zyu.corejava.rmi;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by chenjie on 2016/7/8.
 */
public class ServiceConsumer {

    private static Logger logger = LoggerFactory.getLogger(ServiceConsumer.class);

    // 用于等待 SyncConnected 事件触发后继续执行当前线程
    private CountDownLatch latch = new CountDownLatch(1);

    // 定义一个 volatile 成员变量，用于保存最新的 RMI 地址
    // （考虑到该变量或许会被其它线程所修改，一旦修改后，该变量的值会影响到所有线程）
    private volatile List<String> urlList = new ArrayList<String>();


    // 构造器


    public ServiceConsumer() {
        ZooKeeper zooKeeper = connectServer();
        if (zooKeeper != null){
            watchNode(zooKeeper); // 观察 /registry 节点的所有子节点并更新 urlList 成员变量
        }
    }

    public <T extends Remote> T lookup(){
        T service  = null;
        int size = urlList.size();
        if (size > 0 ){
            String url;
            if (size == 1){
                url = urlList.get(0);// 若 urlList 中只有一个元素，则直接获取该元素
                logger.debug("using only url：{}",url);
            }else {
                Random random = new Random(size);
                url = urlList.get(random.nextInt());
                logger.debug("using random url：{}",url);
            }
            service = lookupService(url);
        }
        return service;
    }

    /**
     * 在 JNDI 中查找 RMI 远程服务对象
     * @param url
     * @param <T>
     * @return
     */
    private <T extends Remote> T lookupService(String url) {

        T remote = null;
        try {
            remote = (T) Naming.lookup(url);
        } catch (Exception e) {
           if (e instanceof ConnectException){
               logger.error("ConnectException -> url :{}",url);
               if (urlList.size() != 0){
                   url = urlList.get(0);
                   return lookupService(url);
               }
           }
            logger.error("",e);
        }

        return remote;
    }

    /**
     * 观察 /registry 节点下所有子节点是否有变化
     * @param zooKeeper
     */
    private void watchNode(final ZooKeeper zooKeeper) {

        try {
            List<String> nodeList = zooKeeper.getChildren(ZkConstant.ZK_REGISTRY_PATH, new Watcher() {
                public void process(WatchedEvent event) {

                    if (event.getType() == Event.EventType.NodeChildrenChanged){
                        watchNode(zooKeeper);// 若子节点有变化，则重新调用该方法（为了获取最新子节点中的数据）
                    }
                }
            });
            List<String> dataList = new ArrayList<String>();
            for (String node: nodeList){
                byte[] data = zooKeeper.getData(ZkConstant.ZK_REGISTRY_PATH + "/" + node, false, null);
                dataList.add(new String(data));
            }
            logger.debug("node data: {}",dataList);
            urlList = dataList;

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     *  连接 ZooKeeper 服务器
     * @return
     */
    private ZooKeeper connectServer() {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(ZkConstant.ZK_CONNECTION_STRING, ZkConstant.ZK_SESSION_TIMEOUT, new Watcher() {
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown(); // 唤醒当前正在执行的线程
                    }
                }
            });
            latch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }

}
