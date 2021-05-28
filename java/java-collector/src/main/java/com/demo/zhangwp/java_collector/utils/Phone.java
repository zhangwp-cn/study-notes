package com.demo.zhangwp.java_collector.utils;

import org.apache.commons.lang3.StringUtils;

public class Phone {

    private static final String[] PHONE_NUMBER_REMOVABLES = {
            "+86", "0086", "86",
    };

    private static int MIN_PHONE_NUMBER_LENGTH = 6;
    private static int MAX_PHONE_NUMBER_LENGTH = 20;

    public static String checkPhoneNumber(String phone) {
        if (phone == null) {
            return null;
        }

        phone = phone.trim();

        for (String prefix : PHONE_NUMBER_REMOVABLES) {
            if (phone.startsWith(prefix)) {
                phone = phone.substring(prefix.length());
                break;
            }
        }
        if (!StringUtils.isNumeric(phone)) {
            return null;
        }
        if (phone.length() < MIN_PHONE_NUMBER_LENGTH || phone.length() > MAX_PHONE_NUMBER_LENGTH) {
            return null;
        }
        return phone;
    }

    public static String hidePhoneNumber(String phone) {
        if (phone == null) {
            return null;
        }
        int n = phone.length();

        return phone.substring(0, 3) + StringUtils.repeat('*', n - 7) + phone.substring(n - 4, n);
    }

}
