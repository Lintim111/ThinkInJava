package com.lintim111.dubbo.demo.provider;

public class DemoServiceImpl2 implements DemoService {

    @Override
    public String sayHello(String name) {
        return "DemoServiceImpl2: Hello " + name;
    }
}