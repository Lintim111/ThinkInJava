package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class StaticLoggerBinder_LodeClassByNameInJVM implements LoggerFactoryBinder {
    @Override
    public ILoggerFactory getLoggerFactory() {
        return null;
    }

    @Override
    public String getLoggerFactoryClassStr() {
        return null;
    }

    public static void main(String[] args) throws IOException {
        Enumeration paths= StaticLoggerBinder_LodeClassByNameInJVM.class.getClassLoader().getResources("org/slf4j/impl/StaticLoggerBinder.class");
        while (paths.hasMoreElements()){
            URL path = (URL) paths.nextElement();
            System.out.println(path.getPath());
        }
    }
}
