package com.vinhle.server.framework.internal.redis;

import com.github.luben.zstd.Zstd;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;

/**
 * Created by VinhLe on 3/18/2017.
 */
public class RedisProvider implements BaseCacheProvider {

    private JedisPool pool = null;
    private RedisConfig config = null;

    @Override
    public void setPoolConfig(RedisConfig config) {
        this.config = config;
    }

    @Override
    public void connect() {
        if (config != null && (pool == null || pool.isClosed())) {
            JedisPoolConfig config = new JedisPoolConfig();
            pool = new JedisPool(config, this.config.getHost(), this.config.getPort(), this.config.getSocketTimeout(), this.config.getPassword());
        }
    }

    @Override
    public void ping() throws IOException {
        Jedis jedis = borrow();
        try {
            jedis.ping();
        } finally {
            revert(jedis);
        }
    }

    @Override
    public void set(byte[] key, byte[] value) throws IOException {
        bSetJ(key, this.config.getExpiredTime(), value);
    }

    @Override
    public byte[] get(byte[] key) throws IOException {
        return bGetJ(key);
    }

    @Override
    public byte[] expire(byte[] key) throws IOException {
        Jedis jedis = borrow();
        byte[] value = null;
        try {
            value = jedis.get(key);
            jedis.expire(key, 0);
        } finally {
            revert(jedis);
            return value;
        }
    }

    @Override
    public void flushdb() throws IOException {
        Jedis jedis = borrow();
        try {
            jedis.flushDB();
        } finally {
            revert(jedis);
        }
    }

    @Override
    public long dbsize() throws IOException {
        Jedis jedis = borrow();
        long size = 0;
        try {
            size = jedis.dbSize();
        } finally {
            revert(jedis);
            return size;
        }
    }

    public interface Callback {
        void execute(Jedis jedis);
    }

    private Jedis borrow() {
        if (pool == null || pool.isClosed()) connect();
        return pool.getResource();
    }

    private void destroy() {
        pool.destroy();
    }

    private void revert(Jedis jedis) {
        pool.returnResource(jedis);
    }

    private void operate(Callback callback) {
        Jedis jedis = borrow();
        try {
            callback.execute(jedis);
        } finally {
            revert(jedis);
        }
    }

    private String info() {
        Jedis jedis = borrow();
        try {
            return jedis.info();
        } finally {
            revert(jedis);
        }
    }

    private String set(String key, String value) {
        Jedis jedis = borrow();
        try {
            return jedis.set(key, value);
        } finally {
            revert(jedis);
        }
    }

    private String set(String key, int seconds, String value) {
        Jedis jedis = borrow();
        try {
            return jedis.setex(key, seconds, value);
        } finally {
            revert(jedis);
        }
    }

    private String get(String key) {
        Jedis jedis = borrow();
        try {
            return jedis.get(key);
        } finally {
            revert(jedis);
        }
    }

    private void del(String key) {
        Jedis jedis = borrow();
        try {
            jedis.del(key);
        } finally {
            revert(jedis);
        }
    }

    private boolean exits(String key) {
        Jedis jedis = borrow();
        try {
            return jedis.exists(key) || jedis.exists(key.getBytes());
        } finally {
            revert(jedis);
        }
    }

    private String bSet(String key, String value) {
        Jedis jedis = borrow();
        try {
            // return OK if success
            return jedis.set(key.getBytes(), Zstd.compress(value.getBytes()));
        } finally {
            revert(jedis);
        }
    }

    private String bSet(String key, String value, int seconds) {
        Jedis jedis = borrow();
        try {
            // return OK if success
            return jedis.setex(key.getBytes(), seconds, Zstd.compress(value.getBytes()));
        } finally {
            revert(jedis);
        }
    }

    private void bDel(String key) {
        Jedis jedis = borrow();
        try {
            jedis.del(key.getBytes());
        } finally {
            revert(jedis);
        }
    }

    private byte[] bGetJ(byte[] key) {
        Jedis jedis = borrow();
        try {
            byte[] value = jedis.get(key);
            if (value != null) return Zstd.decompress(value, (int) Zstd.decompressedSize(value));
            return null;
        } finally {
            revert(jedis);
        }
    }

    private String bSetJ(String key, byte[] value) {
        Jedis jedis = borrow();
        try {
            return jedis.set(key.getBytes(), Zstd.compress(value));
        } finally {
            revert(jedis);
        }
    }

    private String bSetJ(byte[] key, int seconds, byte[] value) {
        Jedis jedis = borrow();
        try {
            return jedis.setex(key, seconds, Zstd.compress(value));
        } finally {
            revert(jedis);
        }
    }
}
