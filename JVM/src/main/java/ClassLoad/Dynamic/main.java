package ClassLoad.Dynamic;

import ClassLoad.Dynamic.classes.baseClass;

public class main {
    private static void loadByClassLoad(ClassLoader loader, String args[]){
        System.out.println("load from "+ loader.getClass().getName());
        for(int i=1;i<args.length;i++){
            try{
                Class c = loader.loadClass(args[i]);
                Object o =c.newInstance();
                baseClass base = (baseClass)o;
                base.sayHello();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadByForName(String[] args){
        for(int i=1;i<args.length;i++){
            Class c;
            try {
                c = Class.forName(args[i]);
            } catch (ClassNotFoundException e) {
                System.out.println("Failed to load "+args[i]);
                continue;
            }
            try {
                Object o = c.newInstance();
                baseClass base = (baseClass)o;
                base.sayHello();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        if(args.length<1){
            System.out.println("please enter classPath and className");
            return;
        }

        ClassLoader classLoader= new jdk1_classLoad(args[0]);

        loadByClassLoad(classLoader,args);
        System.out.println("--------");
        classLoader= new jdk2_classLoad(args[0]);

        loadByClassLoad(classLoader,args);
        System.out.println("---------");
        loadByForName(args);
    }
}
