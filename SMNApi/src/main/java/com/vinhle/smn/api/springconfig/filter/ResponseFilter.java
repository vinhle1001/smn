package com.vinhle.smn.api.springconfig.filter;

import com.github.luben.zstd.Zstd;
import com.vinhle.smn.api.springconfig.wrapper.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nguyenthuantan on 9/30/16.
 */
public class ResponseFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
        filterChain.doFilter(request, responseWrapper);

        //get data
        byte[] data = responseWrapper.getDataStream();
        byte[] compressed = Zstd.compress(data);

        //write response
//        response.setContentLength(compressed.length);
//        response.getOutputStream().write(compressed);
        response.getOutputStream().write(data);
    }

    @Override
    public void destroy() {
    }
}