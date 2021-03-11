package com.example.springdemo2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwp
 * @date 2021/3/11 - 11:59
 */
@RestController
@RequestMapping("/api/v1/test")
@Api(value = "测试接口", tags = "test")
@Slf4j
public class TestController {

    // 获取 卡 列表
    @GetMapping("/health")
//    @ApiOperation(value = "健康检查 接口", hidden = true)
    @ApiOperation("健康检查 接口")
    @ResponseBody
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功！"),
            @ApiResponse(code = 400, message = "参数缺失，请求无效！"),
            @ApiResponse(code = 401, message = "未登录"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 500, message = "内部服务错误！"),
    })
    public String health() throws Exception {

        log.trace("进入控制器: GetMapping");

        String message = "It's healthy";

        return message;
    }

}
