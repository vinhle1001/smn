package com.vinhle.server.framework.client.builder;

import com.vinhle.server.framework.client.cache.search.ConcurrentIndexHandler;
import com.vinhle.server.framework.client.cache.search.IndexHandler;
import com.vinhle.server.framework.client.cache.search.Query;
import com.vinhle.server.framework.client.cache.search.index.IndexType;

import java.util.List;

/**
 * Created by VinhLe on 3/28/2017.
 */
public abstract class SearchableCacheBuilder {
    /**
     * The is searchable.
     */
    protected volatile boolean isSearchable = false;

    /**
     * The query executer.
     */
    protected IndexHandler<Object, Object> indexHandler;

    /**
     * The Constant QUERY_EXECUTER.
     */
    protected static final IndexHandler<Object, Object> QUERY_EXECUTER = new IndexHandler<Object, Object>() {
        public void addIndex(String attributeName, IndexType type) {
        }

        public void remove(Object key, Object value) {
        }

        public List<Object> execute(Query query) {
            return null;
        }

        public void clear() {
        }

        public void add(Object key, Object value) {
        }
    };

    /**
     * Instantiates a new searchable writeCache builder.
     */
    protected SearchableCacheBuilder() {
        super();
        indexHandler = QUERY_EXECUTER;
    }

    /**
     * Searchable.
     */
    protected void searchable() {
        if (!isSearchable) {
            indexHandler = new ConcurrentIndexHandler<Object, Object>();
            isSearchable = true;
        }
    }
}
