package com.zyu.corejava.rmi;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

/**
 * RMI服务提供者
 * Created by chenjie on 2016/7/8.
 */
public class ServiceProvider {

    private static Logger logger = LoggerFactory.getLogger(ServiceProvider.class);

    // 用于等待 SyncConnected 事件触发后继续执行当前线程
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    // 发布 RMI 服务并注册 RMI 地址到 ZooKeeper 中
    public void publish(Remote remote, String host, int port){
        String url =publishService(remote,host,port);
        if (url != null){
            ZooKeeper zk = connectServer();// 连接 ZooKeeper 服务器并获取 ZooKeeper 对象
            if (zk != null){
                createNode(zk,url);
            }
        }
    }

    /**
     * 创建 ZNode
     * @param zk
     * @param url
     */
    private void createNode(ZooKeeper zk, String url) {
        byte[] data = url.getBytes();
        try {
            String path = zk.create(ZkConstant.ZK_PROVIDER_PATH,data,
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.debug("create zookeeper node({}=>{})",path,url);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 连接 ZooKeeper 服务器
    private ZooKeeper connectServer() {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(ZkConstant.ZK_CONNECTION_STRING, ZkConstant.ZK_SESSION_TIMEOUT, new Watcher() {
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected){
                        countDownLatch.countDown(); // 唤醒当前正在执行的线程
                    }
                }
            });
            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }

    private String publishService(Remote remote, String host, int port) {
        String url = null;
        url = String.format("rmi://%s:%d/%s",host,port,remote.getClass().getName());
        try {
            LocateRegistry.createRegistry(port);
            Naming.rebind(url,remote);
            logger.debug("publish rmi service: {}",url);
        } catch (RemoteException e) {
            logger.error("remote error:{}",e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            logger.error("MalformedURL error:{}",e.getMessage());
            e.printStackTrace();
        }
        return url;
    }

}
