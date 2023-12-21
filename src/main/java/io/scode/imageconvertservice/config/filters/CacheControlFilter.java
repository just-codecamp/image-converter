package io.scode.imageconvertservice.config.filters;

import jakarta.servlet.*;

import java.io.IOException;

@Deprecated
public class CacheControlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request
            , ServletResponse response
            , FilterChain chain
    ) throws IOException, ServletException {

    }

}
