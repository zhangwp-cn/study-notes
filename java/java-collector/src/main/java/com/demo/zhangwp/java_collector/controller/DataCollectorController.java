package com.demo.zhangwp.java_collector.controller;

import com.demo.zhangwp.java_collector.pojo.BaseResp;
import com.demo.zhangwp.java_collector.pojo.DataCollectorGaugeReq;
import com.demo.zhangwp.java_collector.service.DataCollectorService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/data-collector")
@Api(tags = "data-collector", description = "监控数据采集 相关接口")
@Slf4j
public class DataCollectorController {

    @Autowired
    private DataCollectorService dataCollectorService;


    // 更新Gauge数据
    @PostMapping("/")
    @ApiOperation(value = "发送 监控数据")
    @ResponseBody
    @ApiResponses({
            @ApiResponse(code = 200, message = "调用成功！"),
            @ApiResponse(code = 400, message = "参数缺失，请求无效！"),
            @ApiResponse(code = 401, message = "未登录"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 500, message = "内部服务错误！"),
    })
    public BaseResp sendSingleGaugeData(@ApiParam(value = "数据信息", required = true) @RequestBody DataCollectorGaugeReq dataCollectorReq) throws Exception {

        log.trace("进入控制器：PostMapping");
        // TODO 参数校验
        BaseResp baseResp = new BaseResp();

        // 判断合法性
        boolean legal = dataCollectorService.isLegalSingleGaugeDataCollectorReq(dataCollectorReq, baseResp);

        if (!legal)
            return baseResp;

        baseResp = dataCollectorService.sendSingleGaugeData(dataCollectorReq);

        return baseResp;
    }

}
