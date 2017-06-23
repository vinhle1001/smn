package com.vinhle.server.framework.internal.type;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import java.sql.*;

/**
 * Created by VinhLe on 3/23/2017.
 */
public class ArrayLong extends ArrayPrimitiveType {

    @Override
    public Class returnedClass() {
        return long[].class;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        Array array = resultSet.getArray(names[0]);
        if(array!=null) {
            Long[] javaArray = (Long[]) array.getArray();
            return ArrayUtils.toPrimitive(javaArray);
        }
        return ArrayUtils.toPrimitive(new Long[0]);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        Connection connection = preparedStatement.getConnection();

        int[] castObject = (int[]) value;
        Integer[] integers = ArrayUtils.toObject(castObject);
        Array array = connection.createArrayOf("bigint", integers);

        preparedStatement.setArray(index, array);
    }
}
