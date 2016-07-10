package com.zyu.corejava.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;

/**
 * Created by travy on 2016/7/9.
 */
public class ZkServer {

    private static Logger logger = LoggerFactory.getLogger(ZkServer.class);

    public static void main(String[] args){
        if (args.length != 2){

            logger.debug("please using command: java Server <rmi_host> <rmi_port>");
            System.exit(-1);
        }

        String host = args[0];
        int port = Integer.parseInt(host);

        ServiceProvider serviceProvider = new ServiceProvider();
        try {
            IService service = new IServiceImpl("zkService");
            serviceProvider.publish(service,host,port);
            Thread.sleep(Long.MAX_VALUE);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
