package com.vinhle.server.framework.internal.type;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;

/**
 * Created by VinhLe on 3/23/2017.
 */
public abstract class ArrayPrimitiveType implements UserType {

    public int[] sqlTypes() {
        return new int[]{Types.ARRAY};
    }

    public boolean equals(Object o, Object o1) throws HibernateException {
        return o.equals(o1);
    }

    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object o) throws HibernateException {
        return null;
    }

    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return null;
    }

    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return null;
    }
}
