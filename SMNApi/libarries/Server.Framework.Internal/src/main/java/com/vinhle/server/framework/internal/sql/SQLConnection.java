package com.vinhle.server.framework.internal.sql;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

/**
 * Created by VinhLe on 3/24/2017.
 */
public class SQLConnection {

    private static SQLConnection ourInstance = new SQLConnection();
    private static final Logger logger = Logger.getLogger(SQLConnection.class.getName());

    public static SQLConnection getInstance() {
        if (ourInstance == null) ourInstance = new SQLConnection();
        return ourInstance;
    }

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
//            final Configuration configuration = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//            configuration.getClassMappings();
//            final ServiceRegistry sr = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties())
//                    .build();
//            sessionFactory = configuration.buildSessionFactory(sr);
//            sessionFactory.getAllClassMetadata();
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * @Singleton template
     */
    private SQLConnection() {
    }
}
