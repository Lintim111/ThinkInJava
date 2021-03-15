package classloader;

import sun.misc.Launcher;

import java.net.URL;

import static java.lang.ClassLoader.getSystemClassLoader;

public class ClassLoaderTest {
    public static void main(String[] args) {

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        Launcher launcher= sun.misc.Launcher.getLauncher();
        ClassLoader loader = getSystemClassLoader();

        for(URL url : urls){
            System.out.println(url.toExternalForm());
        }
    }

    class CustomClassLoader extends ClassLoader{

    }

}
