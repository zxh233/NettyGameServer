package com.snowcattle.game;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test extends Thread {

    static ServerSocket serverSocket;
    static Socket socket;

    static String jiehsou = null;
    static OutputStream out;
    static InputStream in;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(7090);
        socket = serverSocket.accept();
        in = socket.getInputStream();
        out = socket.getOutputStream();
        iscid = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    dealMessage();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
        while (true) {
            receiveMessage();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }


    }

    static boolean iscid = false;

    static Object lock = new Object();

    private static void receiveMessage() {


        System.out.println("Client Connected");

        int len = 0;
        byte[] buf = new byte[88];
        try {
            while ((len = in.read(buf)) != -1) {
                String s = fanfabao1(buf);
                System.out.println("client saying: " + s);
                synchronized (lock) {
                    jiehsou = s;
                    if (iscid) {
                        jiehsou = s + "1422";
                    }
                    lock.notifyAll();
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void dealMessage() {
        synchronized (lock) {
            System.out.println("dealMessage" + jiehsou);
            if (jiehsou == null) {
                lock.notifyAll();

                return;
            }
            if (jiehsou.contains("�\u0016\u0016kR\u0006`4")) {
                jiehsou = null;
                lock.notifyAll();
                return;
            }
            if (jiehsou.contains("2471520192") && jiehsou.contains("hjl1422945131")) {

                iscid = true;
                try {
                    jiehsou = null;
                    out.write(new byte[]{86, 83, 0, 0, -67, -91,-127, -30, -117, -17, -47, -102, -49, -95, -8, -65, -35, -22, -69, -20, -95, -54, -82, -12, -75, -40, -97, -56, -102, -112});
                    out.flush();
                    lock.notifyAll();

                    return;
                } catch (IOException e) {
                    lock.notifyAll();

                    e.printStackTrace();
                    return;
                }
            }
            if (iscid) {
                try {
                    out.write(new byte[]{72, 84, 0, 0, -103, -91,-89, -121, -115, -79, -33, -70, -51, -84, -49, -84, -110, -96, -108, -93, -110, -89, -107, -91, -108, -83, -97, -91, -51, -89, -53, -6, -50, -4, -50, -9, -61, -10, -57, -12, -59, -49, -13, -127, -65, -36, -76, -43, -89, -44, -79, -59, -71, -30, -45, -114, -69, -120, -78, 90, -43, 84, -68, 31, -103, -109});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                lock.notifyAll();

            }
        }

    }

    /**
     * 输入抓包得到的由客户端发送的16进制字符串，再用工具转字符串
     *
     * @param bytes
     * @return
     */
    private static String fanfabao1(byte[] bytes) {
        System.out.println(bytes.length);
        int length = bytes.length;
        for (int i = length - 1; i > 6; i--) {
            bytes[i] ^= bytes[i - 1];
        }
        bytes[6] ^= bytes[4];
        //复制第6个之后的元素
        byte[] bytes6 = new byte[bytes.length - 6];
        System.arraycopy(bytes, 6, bytes6, 0, bytes6.length);
        return Util.byteArrayToString(bytes6);
    }


}
