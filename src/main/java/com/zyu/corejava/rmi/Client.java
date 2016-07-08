package com.zyu.corejava.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by chenjie on 2016/7/8.
 */
public class Client {

    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args){
        String url = "rmi://localhost:8888/";

        try {
            // 在RMI服务注册表中查找名称为service01的对象，并调用其上的方法
            IService iService = (IService) Naming.lookup(url+"service01");

            Class stubClass = iService.getClass();

            logger.debug("{} 是 {} 的实例",iService,stubClass.getName());

            // 获得本底存根已实现的接口类型

            Class[] interfaces = stubClass.getInterfaces();
            for (Class c : interfaces){
                logger.debug("存根类实现了 {} 接口",c.getName());
            }
            System.out.println((iService.service("你好")));

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
