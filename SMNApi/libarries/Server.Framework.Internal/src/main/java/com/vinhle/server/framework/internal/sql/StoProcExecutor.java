package com.vinhle.server.framework.internal.sql;

import com.vinhle.server.framework.internal.type.ArrayInteger;
import com.vinhle.server.framework.internal.type.ArrayLong;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;

import javax.persistence.ParameterMode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by VinhLe on 3/21/2017.
 */
class StoProcExecutor {

    private static final Logger logger = Logger.getLogger(StoProcExecutor.class.getName());

    private Class<?> getClass(Argument obj) {
        if (obj.getType().isArray()) {
            if (obj.getType().getComponentType() == Integer.class)
                return ArrayInteger.class;
            else if (obj.getType().getComponentType() == Long.class)
                return ArrayLong.class;
        }
        return obj.getType();
    }



    private ProcedureCall prepareStatementCallProc(ProcedureCall pStoreCall, Argument... params) {
        int parameterLength = params == null ? 0 : params.length;
        for (int i = 0; i < parameterLength; i++) {
            Argument arg = params[i];
            if (arg.hasArgumentName()) {
                pStoreCall.registerParameter(arg.getParameterName(), getClass(arg), arg.getMode().equals(Argument.Mode.IN) ? ParameterMode.IN : arg.getMode().equals(Argument.Mode.OUT) ? ParameterMode.OUT : ParameterMode.INOUT);
                pStoreCall.getParameterRegistration(arg.getParameterName()).bindValue(arg.getValue());
            } else {
                pStoreCall.registerParameter(arg.getIndex(), getClass(arg), arg.getMode().equals(Argument.Mode.IN) ? ParameterMode.IN : arg.getMode().equals(Argument.Mode.OUT) ? ParameterMode.OUT : ParameterMode.INOUT);
                pStoreCall.getParameterRegistration(arg.getIndex()).bindValue(arg.getValue());
            }
        }
        return pStoreCall;
    }


    protected <T extends Serializable> T ExecuteScalar(Class clazz, String pStoreExecute, Argument... params) {
        Session session = SQLConnection.getInstance().getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        ProcedureCall procedureCall = prepareStatementCallProc(session.createStoredProcedureCall(pStoreExecute, clazz), params);
        ProcedureOutputs procedureOutputs = procedureCall/*.addSynchronizedEntityClass(clazz)*/.getOutputs();
        ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
        List<T> result = resultSetOutput.getResultList();

        transaction.commit();
        session.disconnect();

        if (result != null && result.size() > 0) return result.get(0);
        return null;
    }

    protected <T extends Serializable> List<T> ExecStoreProc(Class clazz, String pStoreExecute, Argument... params) {
        Session session = SQLConnection.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        ProcedureCall procedureCall = prepareStatementCallProc(session.createStoredProcedureCall(pStoreExecute, clazz), params);
        ProcedureOutputs procedureOutputs = procedureCall/*.addSynchronizedEntityClass(clazz)*/.getOutputs();
        ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
        List<T> result = resultSetOutput.getResultList();

        transaction.commit();
        session.disconnect();
        return result != null ? result : new ArrayList<>();
    }

    protected SessionFactory GetSessionFactory() {
        return SQLConnection.getInstance().getSessionFactory();
    }

}
