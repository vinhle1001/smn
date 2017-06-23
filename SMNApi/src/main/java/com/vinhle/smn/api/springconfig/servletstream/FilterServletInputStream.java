package com.vinhle.smn.api.springconfig.servletstream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * Created by nguyenthuantan on 9/29/16.
 */
public class FilterServletInputStream extends ServletInputStream {

    private byte[] byteArr;
    private int lastIndexRetrieved = -1;
    private ReadListener readListener = null;

    public FilterServletInputStream(byte[] byteArr) {
        this.byteArr = byteArr;
    }

    @Override
    public boolean isFinished() {
        return (lastIndexRetrieved == byteArr.length - 1);
    }

    @Override
    public boolean isReady() {
        // This implementation will never block
        // We also never need to call the readListener from this method, as this method will never return false
        return isFinished();
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        this.readListener = readListener;
        if (!isFinished()) {
            try {
                readListener.onDataAvailable();
            } catch (IOException e) {
                readListener.onError(e);
            }
        } else {
            try {
                readListener.onAllDataRead();
            } catch (IOException e) {
                readListener.onError(e);
            }
        }
    }

    @Override
    public int read() throws IOException {
        int i;
        if (!isFinished()) {
            i = byteArr[lastIndexRetrieved + 1];
            lastIndexRetrieved++;
            if (isFinished() && (readListener != null)) {
                try {
                    readListener.onAllDataRead();
                } catch (IOException ex) {
                    readListener.onError(ex);
                    throw ex;
                }
            }
            return i;
        } else {
            return -1;
        }
    }
}
