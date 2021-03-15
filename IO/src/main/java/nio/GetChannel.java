package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class GetChannel {
    private static final int BUFFERSIZE=1024;
    public static void main(String[] args) throws Exception {
        String filepath="data.txt";
        FileChannel fileChannel = null;
        /*FileChannel fileChannel=new FileOutputStream(filepath).getChannel();
        fileChannel.write(ByteBuffer.wrap("Some text".getBytes()));
        fileChannel.close();*/
        fileChannel = new RandomAccessFile(filepath,"rw").getChannel();
        fileChannel.position(fileChannel.size());
        fileChannel.write(ByteBuffer.wrap(" more text".getBytes()));
        //fileChannel.close();
        fileChannel = new FileInputStream(filepath).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFERSIZE);
        fileChannel.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.print((char)buffer.get());
        }

        System.out.println("encoding");
        buffer.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoding using "+ encoding+ ": "+ Charset.forName(encoding).decode(buffer));
        fileChannel.close();
    }
}
