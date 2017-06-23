package com.vinhle.server.framework.client;

import com.vinhle.server.framework.client.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;

/**
 * Created by VinhLe on 3/28/2017.
 */
public class SpringCache implements org.springframework.cache.Cache {

    /**
     * The writeCache.
     */
    private Cache<Object, Object> cache;

    /**
     * Instantiates a new imcache writeCache.
     *
     * @param cache the writeCache
     */
    @SuppressWarnings("unchecked")
    public SpringCache(Cache<?, ?> cache) {
        this.cache = (Cache<Object, Object>) cache;
    }

    @Override
    public String getName() {
        return cache.getName();
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        if (key == null) {
            return null;
        }
        final Object value = cache.get(key);
        return value != null ? new SimpleValueWrapper(value) : null;
    }

    @Override
    public <T> T get(Object key, Class<T> aClass) {
        if (key == null) {
            return null;
        }
        return (T) cache.get(key);
    }

    @Override
    public <T> T get(Object key, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        cache.put(key, value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {
        cache.invalidate(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
