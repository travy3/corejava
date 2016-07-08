package com.zyu.corejava.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by chenjie on 2016/7/8.
 */
public interface IService extends Remote {

    String service(String str)throws RemoteException;
}
