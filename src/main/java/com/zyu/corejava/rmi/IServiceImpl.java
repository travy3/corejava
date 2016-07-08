package com.zyu.corejava.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by chenjie on 2016/7/8.
 */
public class IServiceImpl extends UnicastRemoteObject implements IService{

    private String name;

    protected IServiceImpl(String name) throws RemoteException {

        this.name = name;
    }

    public String service(String str) throws RemoteException {
        return "server>>"+str;
    }
}
