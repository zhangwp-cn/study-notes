package com.demo.zhangwp.java_collector.service.impl;

import com.demo.zhangwp.java_collector.service.PushGatewayService;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class PushGatewayServiceImpl implements PushGatewayService {

    @Value("${pushgateway.server.address}")
    private String pushgatewayServerAddr;


    @Override
    public boolean pushSingleData(Gauge gaugeCollector, String jobName, Map<String, String> groupingKey) {
        PushGateway pushGateway = new PushGateway(pushgatewayServerAddr);
        try {
            pushGateway.pushAdd(gaugeCollector, jobName, groupingKey);
            return true;
        } catch (IOException e) {
            log.error("push 数据失败");
            log.error(e.getMessage());

            return false;
        } finally {
            log.trace("后续。。。");
        }
    }

}
