package com.bw.com.proxy;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class ServicesHandler implements InvocationHandler {
    static  final  Integer i=1;

    private Object target;
    public ServicesHandler(Object target){
        this.target=target;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Long currentTime = System.currentTimeMillis();
        System.out.println("current time ="+currentTime);
       Object o= method.invoke(target,args);
       System.out.println("invoke time="+(System.currentTimeMillis() -currentTime) +"ms");
       return o;
    }

    static {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        new File("com/sun/proxy").mkdirs();
    }
    public static void main(String[] args) {
        Services s = new ServicesImpl();
        ServicesHandler handler = new ServicesHandler(s);
        Services imp = (Services) Proxy.newProxyInstance(s.getClass().getClassLoader(),
                s.getClass().getInterfaces(),handler);
        imp.doSomething();
        imp.doSomething2();
    }
}
