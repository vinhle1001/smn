package com.vinhle.server.framework.client.redis;

import com.vinhle.server.framework.client.cache.AbstractCache;
import com.vinhle.server.framework.client.cache.CacheLoader;
import com.vinhle.server.framework.client.cache.EvictionListener;
import com.vinhle.server.framework.internal.redis.BaseCacheProvider;
import com.vinhle.server.framework.internal.redis.SerializationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by VinhLe on 3/29/2017.
 */
public class RedisCache<K, V> extends AbstractCache<K, V> {

    protected final Log logger = LogFactory.getLog(RedisCache.class);

    /**
     * Redis provider
     */
    protected BaseCacheProvider redisProvider;

    /**
     * The hit.
     */
    protected AtomicLong hit = new AtomicLong();

    /**
     * The miss.
     */
    protected AtomicLong miss = new AtomicLong();

    /**
     * Instantiates a new abstract writeCache.
     *
     * @param cacheLoader
     * @param evictionListener
     * @param redisProvider
     */
    public RedisCache(String cacheName, CacheLoader<K, V> cacheLoader, EvictionListener<K, V> evictionListener, BaseCacheProvider redisProvider) {
        super(cacheLoader, evictionListener);
        this.redisProvider = redisProvider;
        setName(cacheName);
    }

    @Override
    public void put(K key, V value) {
        try {
            redisProvider.set(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
        } catch (IOException e) {
            logger.error("Redis writeCache got an exception", e);
        }
    }

    @Override
    public V get(K key) {
        try {
            return (V) SerializationUtils.deserialize(redisProvider.get(SerializationUtils.serialize(key)));
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Redis writeCache got an exception", e);
        }
        return null;
    }

    @Override
    public V invalidate(K key) {
        try {
            byte[] serializedKey = SerializationUtils.serialize(key);
            byte[] serializedValue = redisProvider.expire(serializedKey);
            V value = (V) SerializationUtils.deserialize(serializedValue);
            evictionListener.onEviction(key, value);
            return value;
        } catch (ClassNotFoundException | IOException e ) {
            throw new RedisCacheException(e);
        }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public void clear() {
        try {
            redisProvider.flushdb();
        } catch (IOException e) {
            logger.error("Redis writeCache got an exception", e);
        }
    }

    @Override
    public double hitRatio() {
        return hitRatio(hit.get(), miss.get());
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public long size() {
        long result = 0L;
        try {
            result = redisProvider.dbsize();
        } catch (IOException e) {
            logger.error("Redis writeCache got an exception", e);
        } finally {
            return result;
        }
    }
}
