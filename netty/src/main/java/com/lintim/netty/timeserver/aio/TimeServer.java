package com.lintim.netty.timeserver.aio;

public class TimeServer {
    public static void main(String[] args){
        int port=8080;
        if(args!=null&&args.length>0){
            port=Integer.valueOf(args[0]);
        }
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer,"NIO-AsyncTimeServerHandler-001").start();
    }
}
