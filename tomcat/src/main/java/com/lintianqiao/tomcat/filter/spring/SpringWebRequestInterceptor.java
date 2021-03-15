package com.lintianqiao.tomcat.filter.spring;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class SpringWebRequestInterceptor implements WebRequestInterceptor {


    @Override
    public void preHandle(WebRequest webRequest) throws Exception {

    }

    @Override
    public void postHandle(WebRequest webRequest, org.springframework.ui.ModelMap modelMap) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

    }
}
