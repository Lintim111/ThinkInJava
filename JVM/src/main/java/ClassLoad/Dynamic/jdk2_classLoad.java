package ClassLoad.Dynamic;

import java.io.*;

public class jdk2_classLoad extends ClassLoader {
    private String basePath;
    public jdk2_classLoad(String path){
        basePath=path;
    }
    public jdk2_classLoad(ClassLoader classLoader, String path){
        super(classLoader);
        basePath=path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData;

        classData= getTypeFromBasePath(name);
        if(classData==null){
            throw new ClassNotFoundException();
        }
        return defineClass(name,classData,0,classData.length);
    }

    private byte[] getTypeFromBasePath(String typeName){
        FileInputStream fis;
        String fileName = basePath+ File.separatorChar+
                typeName.replace('.',File.separatorChar)+".class";
        try{
            fis = new FileInputStream(fileName);
        }catch (FileNotFoundException e) {
            return null;
        }

        BufferedInputStream bis = new BufferedInputStream(fis);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            int c= bis.read();
            while (c!=-1){
                out.write(c);
                c=bis.read();
            }
        } catch (IOException e) {
            return null;
        }
        return  out.toByteArray();
    }
}
