package ru.fedusiv.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ExampleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest   = (HttpServletRequest) request;

        System.out.println(httpServletRequest.getHeader("User-Agent"));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
