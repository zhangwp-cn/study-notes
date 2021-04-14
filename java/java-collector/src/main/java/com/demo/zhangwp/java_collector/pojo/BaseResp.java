package com.demo.zhangwp.java_collector.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseResp {

    // 处理结果代码
    @ApiModelProperty("处理结果代码")
    private String code;

    // 处理结果信息
    @ApiModelProperty("处理结果信息")
    private String msg;

}
