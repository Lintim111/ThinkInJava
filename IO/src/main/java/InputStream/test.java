package InputStream;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;

public class test {
    public static void main(String[] args) {

        String filePath = "serialize.test.txt";
        File file = new File(filePath);
        try (
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                DataInputStream dataInputStream1 = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            byte[] bytes = new byte[20];
            while (dataInputStream.read(bytes) != -1) {
                System.out.println(bytes);
            }
            dataInputStream.reset();
            while (dataInputStream1.available() != 0) {
                System.out.print((char) dataInputStream1.readByte());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void function(){
        ByteBuffer bf = MappedByteBuffer.allocate(100); // map
        ByteBuffer bf2 =ByteBuffer.allocateDirect(1000); // direct 直接io
    }
}
