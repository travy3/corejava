package com.zyu.corejava.rmi;

/**
 * Created by chenjie on 2016/7/8.
 */
public interface ZkConstant {

    String ZK_CONNECTION_STRING = "localhost:2181";
    int ZK_SESSION_TIMEOUT = 5000;
    String ZK_REGISTRY_PATH = "/registry";
    String ZK_PROVIDER_PATH = ZK_REGISTRY_PATH + "/provider";
}
