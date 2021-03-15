package com.lintianqiao.tomcat.jbossTomcat;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class UseJboss {

    private void JNDITest(){
        try {
            InitialContext context = new InitialContext();
            Object objRef = context.lookup("java:comp/env/ejb/BookDBEJB");
            BookDBEJBHome home = (BookDBEJBHome) PortableRemoteObject.narrow(objRef,com.lintianqiao.tomcat.jbossTomcat.BookDBEJBHome.class);
            home.create();

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
