package com.lintianqiao.tomcat.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class NoteFilter implements Filter {

    private FilterConfig config=null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String name =servletRequest.getParameter("name");
        if(null!=name && name.contains("xi")){
            servletResponse.setContentType("text/html;charset=GB2312");
            PrintWriter out = servletResponse.getWriter();
            out.println("<html><body>");
            out.println("<span> xi is not allowed </span>");
            out.println("</body></html>");

            out.flush();
            return;
        }

        long before = System.currentTimeMillis();

        //调用filter链中其他filter做过滤，遍历完filter后调用xxx方法，将请求发给相应的web组件做处理
        filterChain.doFilter(servletRequest,servletResponse);
        // 处理完后回到这里，继续执行。类似aop的切面

        long after = System.currentTimeMillis();
        String requestName="";
        if(servletRequest instanceof HttpServletRequest){
            requestName=((HttpServletRequest) servletRequest).getRequestURI();
        }

        this.config.getServletContext().log("NoteFilter: "+requestName+" cost "+(after-before)+" ms");
    }
}
