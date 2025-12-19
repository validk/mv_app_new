package com.example.hello.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {

        // Kubernetes pod name is available in HOSTNAME env variable
        MDC.put("pod_name", System.getenv("HOSTNAME"));

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
        // Nothing to clean up
    }
}
