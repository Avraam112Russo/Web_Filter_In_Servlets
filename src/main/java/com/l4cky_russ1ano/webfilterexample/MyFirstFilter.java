package com.l4cky_russ1ano.webfilterexample;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter(
//        value = "/TestServlet",
//        initParams = {
//                @WebInitParam(name = "Name", value = "Russo"),
//                @WebInitParam(name = "Surname", value = "Zaripov")
//        }
//)
public class MyFirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getInitParameter("Name"));
        System.out.println(filterConfig.getInitParameter("Surname"));
        System.out.println("Init filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String email = servletRequest.getParameter("email");
            if (email!= null && email.contains("@") && email.contains(".com")){

                filterChain.doFilter(servletRequest, new ResponseWrapper((HttpServletResponse) servletResponse));
                // send continue custom response object
            } else {
                servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
            }
    }

    @Override
    public void destroy() {
        System.out.println("destroy filter");
    }
}

