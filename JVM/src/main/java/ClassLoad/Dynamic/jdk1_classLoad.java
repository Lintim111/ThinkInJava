package ClassLoad.Dynamic;


import java.io.*;

// override loadClass in jdk1.1 style
public class jdk1_classLoad extends ClassLoader {
    private String basePath;

    public jdk1_classLoad(String path) {
        basePath = path;
    }

    @Override
    public synchronized Class loadClass(String className, boolean resolveIt) throws ClassNotFoundException {
        Class result;
        byte[] classData;
        //check the loaded class cache
        result=findLoadedClass(className);
        if(result!=null){
            return result;
        }

        // check with the primordial class loader
        try{
            result = super.findSystemClass(className);
            return result;
        }catch (ClassNotFoundException e)
        {}

        // don't attempt to load a system file except through the primordial class loader
        if(className.startsWith("java.")){
            throw new ClassNotFoundException();
        }

        // try to load it from basePath directory
        classData= getTypeFromBasePath(className);
        if(classData==null){
            throw new ClassNotFoundException();
        }
        result = defineClass(className,classData,0,classData.length);
        if(resolveIt){
            resolveClass(result);
        }

        return result;
    }

    private byte[] getTypeFromBasePath(String typeName){
        FileInputStream fis;
        String fileName = basePath+ File.separatorChar+
                typeName.replace('.',File.separatorChar)+".class";
        System.out.println("class file: "+fileName);
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
