package com.snowcattle.game;

import java.io.UnsupportedEncodingException;

public class Util {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            int b =  ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
            data[i / 2] = (byte) b;

            //System.out.println("data[i / 2] = " + b + ", i = " + i);
        }
        return data;
    }

    public static String byteArrayToUTF8String(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        System.out.println(sb);
        //System.out.println(sb.toString().equals("4f007941794b7b4d746b03690534003200390d38093a0b157102083909380826175c18281828183678377c35742b655261290744103e502549250b3d0a17273a464c6f026313604076464c6f0c64171d".toUpperCase()));
        return sb.toString();
    }

    public static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        //System.out.println(sb);
        try {
            return new String(bytes,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public static byte hexStringToByte(String s){
        int b =  ((Character.digit(s.charAt(0), 16) << 4)
                + Character.digit(s.charAt(1), 16));
        return (byte) b;
    }
}
