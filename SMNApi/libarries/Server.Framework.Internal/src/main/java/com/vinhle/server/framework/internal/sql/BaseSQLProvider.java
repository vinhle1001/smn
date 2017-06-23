package com.vinhle.server.framework.internal.sql;

/**
 * Created by VinhLe on 3/24/2017.
 */
public interface BaseSQLProvider<Entity, Id> {

    Entity save(Entity e) throws Exception;

    Entity update(Entity e) throws Exception;

    Iterable<Entity> findAll() throws Exception;

    Entity findById(Id id) throws Exception;

    Iterable<Entity> findByIds(Id[] ids) throws Exception;

    boolean exists(Id id) throws Exception;

    Iterable<Entity> findByText(String text) throws Exception;

    Iterable<Entity> save(Iterable<Entity> entities) throws Exception;

    Iterable<Entity> update(Iterable<Entity> entities) throws Exception;
}
