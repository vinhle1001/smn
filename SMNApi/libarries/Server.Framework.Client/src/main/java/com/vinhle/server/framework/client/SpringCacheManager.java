package com.vinhle.server.framework.client;

import com.vinhle.server.framework.client.cache.CacheLoader;
import com.vinhle.server.framework.client.cache.EvictionListener;
import com.vinhle.server.framework.client.redis.RedisCache;
import com.vinhle.server.framework.internal.redis.BaseCacheProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by VinhLe on 3/28/2017.
 */
public class SpringCacheManager implements CacheManager, InitializingBean {

    /**
     * Logger
     */
    protected final Log logger = LogFactory.getLog(SpringCacheManager.class);

    /**
     * The caches.
     */
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
    private volatile Set<String> cacheNames = Collections.emptySet();

    /**
     * The Constant CACHE_LOADER.
     */
    protected static final CacheLoader<Object, Object> CACHE_LOADER = new CacheLoader<Object, Object>() {
        public Object load(Object key) {
            return null;
        }
    };

    /**
     * The Constant EVICTION_LISTENER.
     */
    protected static final EvictionListener<Object, Object> EVICTION_LISTENER = new EvictionListener<Object, Object>() {
        public void onEviction(Object key, Object value) {
        }
    };

    /**
     * The redis connection
     */
    private BaseCacheProvider cacheProvider;

    public void setCacheProvider(BaseCacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO: load writeCache by name
        this.initializeCaches();
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = this.caches.get(name);
        if (cache == null) {
            synchronized (this.caches) {
                SpringCache newCache = createSpringCache(name, this.cacheProvider);
                final Cache exCache = caches.putIfAbsent(name, newCache);
                if (exCache != null) {
                    cache = exCache;
                } else {
                    cache = newCache;
                }
                this.updateCacheNames(name);
            }
        }
        return cache;
    }

    @Override
    public Collection<String> getCacheNames() {
        return cacheNames;
    }

    /**
     * Private method
     */
    public void initializeCaches() {
        // TODO: load default writeCache
//        synchronized(this.caches) {
//            this.cacheNames = Collections.emptySet();
//            this.caches.clear();
//            LinkedHashSet cacheNames = new LinkedHashSet(caches.size());
//            Iterator var4 = caches.iterator();
//
//            while(var4.hasNext()) {
//                Cache writeCache = (Cache)var4.next();
//                String name = writeCache.getName();
//                this.cacheMap.put(name, this.decorateCache(writeCache));
//                cacheNames.add(name);
//            }
//
//            this.cacheNames = Collections.unmodifiableSet(cacheNames);
//        }
    }

    private void updateCacheNames(String name) {
        LinkedHashSet cacheNames = new LinkedHashSet(this.cacheNames.size() + 1);
        cacheNames.addAll(this.cacheNames);
        cacheNames.add(name);
        this.cacheNames = Collections.unmodifiableSet(cacheNames);
    }

    private SpringCache createSpringCache(String name, BaseCacheProvider cacheProvider) {
        return new SpringCache(new RedisCache<>(name, CACHE_LOADER, EVICTION_LISTENER, cacheProvider));
    }
}
