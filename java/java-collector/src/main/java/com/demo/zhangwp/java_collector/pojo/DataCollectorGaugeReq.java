package com.demo.zhangwp.java_collector.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class DataCollectorGaugeReq {

    // 标签值(元素非空，且数组长度与标签值数组长度一致)
    @ApiModelProperty(value = "标签值(元素非空，且数组长度与标签值数组长度一致)", required = true)
    private String[] labelValues;

    // 数据值
    @ApiModelProperty(value = "数据值", required = true)
    private BigDecimal dateValue;

    // 所属任务
    @ApiModelProperty("所属任务")
    private String jobName;

    // 分组标签
    @ApiModelProperty("分组标签")
    private Map<String, String> groupingKey;

}
