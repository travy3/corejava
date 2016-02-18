package com.zyu.corejava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chenjie on 2016/2/14.
 */
public class ProxyFactoryBean implements InvocationHandler {
    //目标对象
    private Object target;


    public void setTarget(Object target){
        this.target = target;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        TransactionManager.begin();
        //调用目标方法
        method.invoke(target,args);
        TransactionManager.end();
        return null;
    }
}
