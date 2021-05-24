package com.demo.zhangwp.java_collector.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwp
 * @date 2021/5/14-18:17
 */
@Slf4j
public class TestUtils {
    public static void main(String[] args) {

        testMethod();
    }

    private static void testMethod() {
        String filePath = "C:\\Users\\zwp\\Desktop\\test\\1440201002925.txt";

        List<String> dataList = TxtUtil.readTxtFile(filePath);

        log.info("开始");

        int i = 1;
        Long start = 0L;
        Long end = 0L;
        List<Long> cardList = new ArrayList<>();
        List<Long> cardListAll = new ArrayList<>();
        for (String data : dataList) {
            Long dataLong = Long.valueOf(data);
            cardList.add(dataLong);

            if (i == 1) {
                start = dataLong;
                end = dataLong;
                i++;
                continue;
            }

            if (dataLong - 1 == end) {
                end = dataLong;
                i++;
            } else {
                if (i > 100) {
                    log.info(start + " - " + end + " 连续 " + (i - 1) + "张");
                    cardListAll.add(dataLong);
                } else {
//                    log.info("连续不足100张：" + cardList);
                    cardListAll.addAll(cardList);
                }

                cardList = new ArrayList<>();
                i = 1;
            }

//            log.info("i: " + i);

//            log.info("data: " + data);
        }


        if (i > 100)
            log.info(start + " - " + end + " 连续 " + (i - 1) + "张");

        log.info("不连续卡列表：" + cardListAll);
    }
}
