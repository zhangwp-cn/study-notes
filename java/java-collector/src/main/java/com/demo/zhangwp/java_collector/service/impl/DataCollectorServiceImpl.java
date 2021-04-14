package com.demo.zhangwp.java_collector.service.impl;

import com.demo.zhangwp.java_collector.pojo.BaseResp;
import com.demo.zhangwp.java_collector.pojo.DataCollectorGaugeReq;
import com.demo.zhangwp.java_collector.service.DataCollectorService;
import com.demo.zhangwp.java_collector.service.PushGatewayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.demo.zhangwp.java_collector.config.CollectorConfig.gaugeCollector1;

@Service
@Slf4j
public class DataCollectorServiceImpl implements DataCollectorService {

    @Autowired
    private PushGatewayService pushGatewayService;


    private boolean isLegalLabelValue(BaseResp baseResp, String[] labelValues) {
        for (String labelValue : labelValues) {
            if (StringUtils.isEmpty(labelValue)) {
                baseResp.setCode("4");
                baseResp.setMsg("标签值不能为空");

                return true;
            }
        }
        return false;
    }

    private void getSendResult(BaseResp baseResp, boolean sendSuccess) {
        if (sendSuccess) {
            baseResp.setCode("2");
            baseResp.setMsg("数据发送成功");
        } else {
            baseResp.setCode("5");
            baseResp.setMsg("数据发送失败");
        }
    }


    @Override
    public boolean isLegalSingleGaugeDataCollectorReq(DataCollectorGaugeReq dataCollectorReq, BaseResp baseResp) {
        log.trace("dataCollectorReq: " + dataCollectorReq);

        if (dataCollectorReq.getDateValue() == null) {
            baseResp.setCode("4");
            baseResp.setMsg("数据值不能为空");

            return false;
        }

        String[] labelValues = dataCollectorReq.getLabelValues();
        if (isLegalLabelValue(baseResp, labelValues)) return false;

        return true;
    }

    @Override
    public BaseResp sendSingleGaugeData(DataCollectorGaugeReq dataCollectorReq) {
        BaseResp baseResp = new BaseResp();

        String jobName = dataCollectorReq.getJobName();
        if (StringUtils.isEmpty(jobName))
            jobName = "demoJob";

        try {
            gaugeCollector1.labels(dataCollectorReq.getLabelValues()).set(dataCollectorReq.getDateValue().doubleValue());
        } catch (IllegalArgumentException e) {
            log.error("标签值个数不匹配");
            log.error(e.getMessage());

            baseResp.setCode("4");
            baseResp.setMsg("标签值个数不匹配");
            return baseResp;
        }
        Map<String, String> groupingKey = dataCollectorReq.getGroupingKey();
        boolean sendSuccess = pushGatewayService.pushSingleData(gaugeCollector1, jobName, groupingKey);

        getSendResult(baseResp, sendSuccess);

        return baseResp;
    }

}
