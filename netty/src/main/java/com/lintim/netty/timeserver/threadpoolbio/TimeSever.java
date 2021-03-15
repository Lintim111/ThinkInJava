package com.lintim.netty.timeserver.threadpoolbio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeSever {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port " + port);
            Socket socket = null;
            TimeServerHandlerExecutorPool singleExecutor = new TimeServerHandlerExecutorPool(50, 10000);
            while (true) {
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }

    static class TimeServerHandlerExecutorPool {
        private ExecutorService executor;

        public TimeServerHandlerExecutorPool(int maxPoolSize, int queueSize) {
            executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
        }

        public void execute(java.lang.Runnable task) {
            executor.execute(task);
        }
    }
}
