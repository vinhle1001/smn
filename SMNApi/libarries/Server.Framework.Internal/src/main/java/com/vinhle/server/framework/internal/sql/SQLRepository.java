package com.vinhle.server.framework.internal.sql;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by VinhLe on 3/24/2017.
 */
public abstract class SQLRepository<Entity, Id> extends StoProcExecutor implements BaseSQLProvider<Entity, Id> {

    @Override
    public Iterable<Entity> save(Iterable<Entity> entities) throws Exception {
        List<Entity> result = new LinkedList<>();
        if (entities == null) return result;

        for (Entity entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    @Override
    public Iterable<Entity> update(Iterable<Entity> entities) throws Exception {
        List<Entity> result = new LinkedList<>();
        if (entities == null) return result;

        for (Entity entity : entities) {
            result.add(update(entity));
        }
        return result;
    }
}
