package com.zyu.corejava.proxy.cglib;

/**
 * Created by chenjie on 2016/2/14.
 */
public class TransactionManager {
    public static void begin(){
        System.out.println("begin...");
    }

    public static void end(){
        System.out.println("end...");
    }
}
