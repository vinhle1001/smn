package com.vinhle.smn.api.springconfig.wrapper;

import com.github.luben.zstd.Zstd;
import com.vinhle.smn.api.springconfig.servletstream.FilterServletInputStream;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class RequestWrapper extends HttpServletRequestWrapper {

    private byte[] data;
    private ServletInputStream inputStream;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        byte[] data1 = IOUtils.toByteArray(request.getInputStream());
        inputStream = new FilterServletInputStream(data = data1 /*data = Zstd.decompress(data1, (int) Zstd.decompressedSize(data1))*/);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return inputStream;
    }
}
