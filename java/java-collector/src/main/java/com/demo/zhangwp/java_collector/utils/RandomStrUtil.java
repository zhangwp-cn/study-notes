package com.demo.zhangwp.java_collector.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class RandomStrUtil {

    public static String makeRandomStr(Integer length, String str) {
        if (length == null || length <= 0)
            length = 16;
        if (str == null || str.length() < 1)
            str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$^";

        int strLength = str.length();
        log.debug("length: " + length);
        log.debug("strLength: " + strLength);

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(strLength);
            log.trace("number: " + number);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Integer length = 32;
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$^";

        String randomStr = makeRandomStr(length, str);

        System.out.println(randomStr);
    }
}
