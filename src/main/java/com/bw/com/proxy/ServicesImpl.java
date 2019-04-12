package com.bw.com.proxy;

public class ServicesImpl  implements Services{
    public void doSomething() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }

    public void doSomething2() {
        System.out.println("222");
    }
}
