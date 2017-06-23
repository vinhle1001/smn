package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnLogEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/24/2017.
 */
@Component
public class LogRepository extends SQLRepository<SmnLogEntity, Integer> {

    @Override
    public SmnLogEntity save(SmnLogEntity e) throws Exception {
        Session currentSession = GetSessionFactory().openSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.save(e);

        transaction.commit();
        currentSession.close();
        return e;
    }

    @Override
    public SmnLogEntity update(SmnLogEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnLogEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnLogEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnLogEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnLogEntity> findByText(String text) throws Exception {
        return null;
    }
}
