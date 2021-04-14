package com.demo.zhangwp.java_collector.service;

import io.prometheus.client.Gauge;

import java.util.Map;

public interface PushGatewayService {

    boolean pushSingleData(Gauge gaugeCollector, String jobName, Map<String, String> groupingKey);
}
