package com.vinhle.server.framework.internal.redis;

import java.io.*;

/**
 * Created by VinhLe on 3/24/2017.
 */
public class SerializationUtils {

    public static byte[] serialize(Object obj) throws IOException {
        byte[] objectBytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            objectBytes = bos.toByteArray();
            out.close();
            bos.close();
        } catch (NullPointerException | IOException e) {
            System.err.println(e.getMessage());
        }
        return objectBytes;
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        Object object = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ObjectInput in = new ObjectInputStream(bis);
            object = in.readObject();
            bis.close();
            in.close();
        } catch (NullPointerException | IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return object;
    }

}
