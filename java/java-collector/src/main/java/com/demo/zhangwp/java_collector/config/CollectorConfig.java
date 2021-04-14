package com.demo.zhangwp.java_collector.config;

import io.prometheus.client.Gauge;
import org.springframework.context.annotation.Configuration;

// 采集点配置
@Configuration
public class CollectorConfig {

    // 定义一个赋值采集点
    public static final Gauge gaugeCollector1 = Gauge.build()
            .name("MONITOR:java_collector:gauge_collector_1")
            .labelNames("label_1_1", "label_1_2")
            .help("赋值采集点-1")
            .register();

    // 定义另一个赋值采集点
    public static final Gauge gaugeCollector2 = Gauge.build()
            .name("MONITOR:java_collector:gauge_collector_2")
            .labelNames("label_2_1", "label_2_2", "label_2_3")
            .help("赋值采集点-2")
            .register();

    // 定义其他采集点
    // ...

}
