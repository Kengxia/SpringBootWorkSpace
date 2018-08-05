package com.ma.framework.utils;

/**
 * @author 华安  mashuai_bj@si-tech.com.cn
 * @Title:
 * @Date: Create in 10:11 2018/4/18
 * @Description:
 */
public class IDUtils {

    private static byte[] lock = new byte[0];

    // 位数，默认是8位
    private final static long w = 10;

    public static String createID() {
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }

        return System.currentTimeMillis() + String.valueOf(r).substring(1);
    }
} 