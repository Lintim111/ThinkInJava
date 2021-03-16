package com.lintim111.dubbo.demo.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

public class NoConfigProvider {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    public static void main(String[] args) throws Exception {
        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl2());
        service.export();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
