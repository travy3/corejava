package com.zyu.corejava.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by chenjie on 2016/7/8.
 */
public class Server {

    private static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args){
        try {
            // 实例化实现了IService接口的远程服务ServiceImpl对象
            IService iService = new IServiceImpl("service01");

            // 本地主机上的远程对象注册表Registry的实例，并指定端口为8888，
            // 这一步必不可少（Java默认端口是1099），必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上

            LocateRegistry.createRegistry(8888);
            // 把远程对象注册到RMI注册服务器上，并命名为service02
            //绑定的URL标准格式为：rmi://host:port/name(其中协议名可以省略，下面两种写法都是正确的）

            Naming.rebind("rmi://localhost:8888/service01",iService);

        } catch (RemoteException e) {
            logger.debug("remote is {}",e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            logger.debug("MalformedURL is {}",e.getMessage());
            e.printStackTrace();
        }

        System.out.println("服务器向命名表注册了1个远程服务对象！");
    }
}
