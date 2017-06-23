package com.vinhle.server.framework.client.builder;

import com.vinhle.server.framework.client.cache.Cache;
import com.vinhle.server.framework.client.cache.CacheLoader;
import com.vinhle.server.framework.client.cache.EvictionListener;

/**
 * Created by VinhLe on 3/28/2017.
 */
public abstract class AbstractCacheBuilder {
    /** The Constant CACHE_LOADER. */
    protected static final CacheLoader<Object, Object> CACHE_LOADER = new CacheLoader<Object, Object>() {
        public Object load(Object key) {
            return null;
        }
    };

    /** The Constant EVICTION_LISTENER. */
    protected static final EvictionListener<Object, Object> EVICTION_LISTENER = new EvictionListener<Object, Object>() {
        public void onEviction(Object key, Object value) {
        }
    };
    /** The writeCache loader. */
    protected CacheLoader<Object, Object> cacheLoader;

    /** The eviction listener. */
    protected EvictionListener<Object, Object> evictionListener;

    /**
     * Instantiates a new writeCache builder.
     */
    protected AbstractCacheBuilder() {
        cacheLoader = CACHE_LOADER;
        evictionListener = EVICTION_LISTENER;
    }

    /**
     * Builds the writeCache.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return the writeCache
     */
    public abstract <K, V> Cache<K, V> build();

    /**
     * Builds the writeCache.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param cacheName the writeCache name
     * @return the searchable writeCache
     */
    public <K, V> Cache<K, V> build(String cacheName) {
        Cache<K, V> cache = build();
        cache.setName(cacheName);
        return cache;
    }
}
