package com.zc.original_test;

import com.zc.config.HexUtil;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date 2021/3/30 14:45
 * @Author zc
 */
public class BinaryReadTest {

    public static void main(String[] args) throws IOException {
        consumeQueue();
        commintlog();
    }

    public static void consumeQueue() throws IOException {
        File file = new File("D:/2");
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        System.out.println("*".hashCode());
        System.out.println(in.available());
        while (true){
            long of = in.readLong();
            int size = in.readInt();
            long tag = in.readLong();
            if(size==0){
                break;
            }
            System.out.println( of+":" + size+ ":" + tag);
        }
        in.close();
        in = null;
    }

    public static void commintlog() throws IOException {
        File file = new File("D:/cl2");
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        System.out.println(in.available());
        int k = 0;
        //可以通过consumeQueue中索引地址读取指定消息
        //in.read(new byte[78922], 0 , 78922);
        while (true){
            int msgSize = in.readInt();
            System.out.println("msgSize" + msgSize);
            if(msgSize ==0){
                break;
            }
            byte[] magic = new byte[4];
            in.read(magic);

            System.out.println("MAGICCODE:  "+ HexUtil.byte2hex(magic));
            System.out.println("BODY CRC" + in.readInt());
            System.out.println("queumsgSizeeId" + in.readInt());
            System.out.println("flag" + in.readInt());
            System.out.println("QUEUEOFFSET" + in.readLong());
            System.out.println("PHYSICALOFFSET" + in.readLong());
            System.out.println("SYSFLAG" + in.readInt());
            String timePattern = "yyyy-MM-dd HH:mm:ss.sss";
            long BORNTIMESTAMP = in.readLong();
            System.out.println("BORNTIMESTAMP" + new SimpleDateFormat(timePattern).format(new Date(BORNTIMESTAMP)));


            byte[] BORNHOST = new byte[4];
            in.read(BORNHOST);
            System.out.println("BORNHOST" + InetAddress.getByAddress(BORNHOST) +":"+ in.readInt());

            System.out.println("STORETIMESTAMP" + in.readLong());

            byte[] STOREHOSTADDRESS = new byte[4];
            in.read(STOREHOSTADDRESS);
            System.out.println("STOREHOSTADDRESS" + InetAddress.getByAddress(STOREHOSTADDRESS) +":"+ in.readInt());

            System.out.println("RECONSUMETIMES" + in.readInt());

            System.out.println("PreparedTransaction Offset" + in.readLong());
            int length = in.readInt();
            System.out.println("messagebodyLength" + length);

            if(length>0){
                byte[] content = new byte[length];
                in.read(content);
                System.out.println("messagebody" + new String(content,"GB18030"));
            }



            int topicLength = (int)in.readByte();
            System.out.println("topicLength" + topicLength);

            byte[] topic = new byte[topicLength];
            in.read(topic);
            System.out.println("topic: " + new String(topic));



            short propertiesLength = in.readShort();
            if(propertiesLength>0){
                byte[] properties = new byte[propertiesLength];
                in.read(properties);
                System.out.println("properties" + new String(properties));
            }

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + (++k));

         }

        in.close();
        in = null;

    }

}
