package com.vinhle.server.framework.internal.redis;

/**
 * Created by VinhLe on 3/29/2017.
 */
public class RedisConfig {

    /**
     * The Constant DEFAULT_TIMEOUT.
     */
    public final static int DEFAULT_TIMEOUT = 1000;

    /**
     * The Constant DEFAULT_SOCKET_TIMEOUT.
     */
    public final static int DEFAULT_SOCKET_TIMEOUT = 30000;

    /**
     * The Constant DEFAULT_HOST.
     */
    public static final String DEFAULT_HOST = "localhost";

    /**
     * The Constant DEFAULT_PORT.
     */
    public static final int DEFAULT_PORT = 6379;

    /**
     * The Constant DEFAULT_HOST.
     */
    public static final String DEFAULT_PASSWORD = null;

    /**
     * The host.
     */
    private String host;

    /**
     * The port.
     */
    private int port;

    /**
     * The expiredTime.
     */
    private int expiredTime;

    /**
     * The socket expiredTime. calculate seconds
     */
    private int socketTimeout;

    /**
     * The socket expiredTime.
     */
    private String password;
    /**
     * Instantiates a new connection.
     */
    public RedisConfig() {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    /**
     * Instantiates a new connection.
     *
     * @param host the host
     */
    public RedisConfig(final String host) {
        this(host, DEFAULT_PORT);
    }

    /**
     * Instantiates a new connection.
     *
     * @param host the host
     * @param port the port
     */
    public RedisConfig(final String host, final int port) {
        this.host = host;
        this.port = port;
        this.expiredTime = DEFAULT_TIMEOUT;
        this.socketTimeout = DEFAULT_SOCKET_TIMEOUT;
        this.password = DEFAULT_PASSWORD;
    }

    /**
     * public method
     *
     * @return
     */

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
