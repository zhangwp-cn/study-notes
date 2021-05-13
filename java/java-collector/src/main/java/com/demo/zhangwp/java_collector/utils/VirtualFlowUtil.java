package com.demo.zhangwp.java_collector.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VirtualFlowUtil {
    private static final Log log = LogFactory.getLog(VirtualFlowUtil.class);

    public static Long transfer(Long realAllFlow, Long realUsed, Long virtualAllFlow) {
        return transfer( realAllFlow,  realUsed,  virtualAllFlow,  1);
    }

    /**
     * 获取虚拟流量
     *
     * @return
     */
    public static Long transfer(Long realAllFlow, Long realUsed, Long virtualAllFlow, Integer k) {
        log.trace(String.format("真实总流量：%s,真实总流量：%s,真实总流量：%s", realAllFlow, realUsed, virtualAllFlow));
        if (realAllFlow == null || realUsed == null || virtualAllFlow == null) {
            return null;
        }
        if(virtualAllFlow.equals(realAllFlow)){
            return realUsed;
        }
        if (virtualAllFlow == 0 || virtualAllFlow.compareTo(realAllFlow) <= 0) {
            return realUsed;
        }

        // K>1
        if (k == null || k <= 1)
            k = 1;

        // K的放大倍率
        double nk = Math.pow(k, 3);
//        Integer nk;
//        switch (k) {
//            case 1:
//                nk = k;
//                break;
//            case 2:
//                nk = 4 * k;
//                break;
//            case 3:
//                nk = 8 * k;
//                break;
//            default:
//                nk = 10 * k;
//                break;
//        }

        // 可变系数（小于3的正浮点数，值越大，可调范围越大）
        double max = 3;

        double virtualUsedFlow = 0l;

        realAllFlow = realAllFlow / 1024;
        realUsed = realUsed / 1024;
        virtualAllFlow = virtualAllFlow / 1024;

        double a = (Math.pow(nk, 2) * Math.pow(realAllFlow, 3)) / max;
        double b = Math.pow(realAllFlow, 2);
        double c = realAllFlow - virtualAllFlow;

        double d = b * b - 4 * a * c;        //根据b^2-4ac判断方程可解性
        log.trace(d);
        if (d < 0) {
            log.trace("方程无解:");
            return null;
        } else if (d == 0) {
            log.trace("方程有一个解:" + -b / (2 * a));
            return null;
        } else {
            log.trace("方程有两个解：" + (-b + Math.sqrt(d)) / (2 * a) + "和" + (-b - Math.sqrt(d)) / (2 * a));
            b = (-b + Math.sqrt(d)) / (2 * a);
        }

        virtualUsedFlow = Math.pow(realUsed, 3) * (Math.pow(nk * b, 2)) / max + b * Math.pow(realUsed, 2) + realUsed;
        log.trace("虚拟使用流量值：" + virtualUsedFlow);
        return new Double(virtualUsedFlow * 1024).longValue();
    }


//    public static void main(String[] args) {
//        Integer realAllFlow = 300;
//        Integer virtualAllFlow = 1500;
//
//        Integer k = 1;
//
//        System.out.println("真实用量总量：" + realAllFlow + "GB（对应MB：" + realAllFlow * 1024 + "MB）");
//        System.out.println("显示用量总量：" + virtualAllFlow + "GB（对应MB：" + virtualAllFlow * 1024 + "MB）");
//        System.out.println("坡度系数（正整数，越大越陡）：" + k);
//
//
//        Integer[] realUseds = {1, 10, 50, 100, 150, 200, 250, 300};
//
//        for (Integer realUsed : realUseds) {
//            System.out.println("");
//            Long transfer = transfer(realAllFlow * 1024 * 1024L, realUsed * 1024 * 1024L, virtualAllFlow * 1024 * 1024L, k);
//            System.out.println("实际用量：" + realUsed + "GB（对应MB：" + realUsed * 1024 + "MB）");
//            System.out.println("显示用量：" + transfer / 1024 / 1024 + "GB（对应MB：" + transfer / 1024 + "MB）");
//        }
//    }

}
