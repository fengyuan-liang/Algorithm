package com.fx.creationalpatterns.prototypepattern.util;


import java.io.*;

/**
 * 拷贝Bean
 */
public final class BeanUtil {

    private BeanUtil() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepClone(T object) {
        T cloneObject = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            cloneObject = (T) objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return cloneObject;
    }
}
