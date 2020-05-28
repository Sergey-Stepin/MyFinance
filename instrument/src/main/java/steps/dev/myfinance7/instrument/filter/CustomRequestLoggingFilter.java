/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.instrument.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 *
 * @author stepin
 */

//@Order(1)
//@Component
//public class CustomRequestLoggingFilter extends GenericFilterBean {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
//        //final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        final ContentCachingRequestWrapper request = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
//        
//        String body;
//        try(BufferedReader reader = request.getReader()){
//            body = reader.lines().collect(Collectors.joining());
//        }
//        
//        System.out.println("~ LogRequest~ " 
//                + "METHOD:" + request.getMethod() + " "
//                + request.getContextPath() + " "
//                + "BODY: " + body);
//        
//        chain.doFilter(servletRequest, servletResponse);
//    }
//}