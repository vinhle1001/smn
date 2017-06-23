package com.vinhle.server.framework.internal.redis;

import java.io.IOException;

/**
 * Created by VinhLe on 3/27/2017.
 */
public interface BaseCacheProvider {

    /**
     * @param config
     */
    void setPoolConfig(RedisConfig config);
    /**
     * Action connect
     *
     * @throws IOException
     */
    void connect();

    /**
     * Ping the server. This command is often used to test if a connection
     * is still alive.
     *
     * @throws IOException
     */
    void ping() throws IOException;

    /**
     * Set key to hold the value. If key already holds a value,
     * it is overwritten.
     *
     * @param key   the key
     * @param value the value
     * @throws IOException
     */
    void set(byte[] key, byte[] value) throws IOException;

    /**
     * Get the value of key. If the key does not exist the special
     * value null is returned.
     *
     * @param key the key
     * @return the byte[]
     * @throws IOException
     */
    byte[] get(byte[] key) throws IOException;

    /**
     * Set a timeout on key. After the timeout has expired, the key will
     * automatically be deleted.
     *
     * @param key the key
     * @return the byte[]
     * @throws IOException
     */
    byte[] expire(byte[] key) throws IOException;

    /**
     * Delete all the keys of the currently selected DB.
     *
     * @throws IOException
     */
    void flushdb() throws IOException;

    /**
     * Return the number of keys.
     *
     * @return the int
     * @throws IOException
     */
    long dbsize() throws IOException;

}
