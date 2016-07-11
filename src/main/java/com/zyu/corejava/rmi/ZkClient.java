package com.zyu.corejava.rmi;

import java.rmi.RemoteException;

/**
 * Created by chenjie on 2016/7/11.
 */
public class ZkClient {

    public static void main(String[] args){
        ServiceConsumer consumer = new ServiceConsumer();

        while(true){
            IService iService  = consumer.lookup();
            try {
                String result =iService.service("zyu");
                System.out.println(result);
                Thread.sleep(3000);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
