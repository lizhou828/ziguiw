package com.arj.ziguiw.common.utils;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: pengchangguo
 * Date: 12-1-12
 * Time: P.M 12:06
 */
public class ObjectSerializer {

    public static byte[] serialize(Object o) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        try {
            oos.writeObject(o);
            oos.flush();
        } finally {
            oos.close();
        }
        return bos.toByteArray();
    }

    public static Object reSerialize(byte[] bytes) throws IOException, ClassNotFoundException {
        Object o;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        try {
            o = ois.readObject();
        } finally {
            ois.close();
        }
        return o;
    }
}
