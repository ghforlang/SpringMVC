package com.nbu.edu.cn.asession.core;

import javax.servlet.*;
import java.io.IOException;

public class ASessionFilter<S extends ASession> implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
