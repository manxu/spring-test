package com.zc.original_test;

import com.zc.model.Red;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Date 2021/3/26 17:09
 * @Author zc
 * 序列化
 */
public class SerializableTest {

    public static void main(String[] args) throws IOException {
        FileOutputStream file = new FileOutputStream("D:/aa.txt");
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(new Red());
        out.close();
        file.close();

        try {
            FileInputStream inf = new FileInputStream("D:/aa.txt");
            ObjectInputStream in = new ObjectInputStream(inf);
            Object c = in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
