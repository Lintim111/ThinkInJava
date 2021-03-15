package com.lintim.springboot.configuration;

import com.lintim.springboot.beans.HelloBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean(name = "HelloBean", value = "HelloBean")
    public HelloBean helloBean(){
        HelloBean bean = new HelloBean();
        bean.setId(1);
        bean.setName("Hello 1");
        return bean;
    }
}
