package com.lintim.springboot;

import com.lintim.springboot.beans.HelloBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration()
@ComponentScan(basePackages = "com.lintim.springboot")
public class Main {
    @Resource
    private HelloBean helloBean;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    @RequestMapping("/hello")
    @PostMapping
    String hello() {
        HttpRequest request;
        String name = helloBean.getName();
        return name;
    }

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
        //System.out.println(helloBean.getName());
        // springMVC的核心类, extends HttpServlet实现了Servlet接口，默认为tomcat的servlet配置
        DispatcherServlet servlet = new DispatcherServlet();
    }
}
