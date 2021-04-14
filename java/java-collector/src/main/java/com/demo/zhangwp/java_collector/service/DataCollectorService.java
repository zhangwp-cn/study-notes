package com.demo.zhangwp.java_collector.service;

import com.demo.zhangwp.java_collector.pojo.BaseResp;
import com.demo.zhangwp.java_collector.pojo.DataCollectorGaugeReq;

public interface DataCollectorService {

    boolean isLegalSingleGaugeDataCollectorReq(DataCollectorGaugeReq dataCollectorReq, BaseResp baseResp);

    BaseResp sendSingleGaugeData(DataCollectorGaugeReq dataCollectorReq);
}
