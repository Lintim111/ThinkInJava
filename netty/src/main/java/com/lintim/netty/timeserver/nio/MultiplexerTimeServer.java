package com.lintim.netty.timeserver.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;

    public MultiplexerTimeServer(int port){
        try{
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port: "+port);

        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        this.stop=true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                /*if (!selector.isOpen()) {
                    System.out.println("selector is closed");
                    break;
                }*/
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    handleInput(key);
                    if (key != null) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc=ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                //Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("The time server received order: "+body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new java.util.Date(System.currentTimeMillis()).toString():"BAD ORDER";
                    doWrite(sc,currentTime);
                }else if(readBytes<0){
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                }else
                    ;//读到0字节, 忽略
            }
        }
    }

    private void doWrite(SocketChannel channel,String response) throws IOException {
        if(response!=null&&response.trim().length()>0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
