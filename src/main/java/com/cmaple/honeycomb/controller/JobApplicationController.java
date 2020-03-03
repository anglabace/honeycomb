package com.cmaple.honeycomb.controller;

import com.cmaple.honeycomb.service.JobApplicationService;
import com.cmaple.honeycomb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名：岗位申请管理服务控制器 - JobApplicationController
 * 功能描述： 岗位申请管理服务控制器
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：NULL
 * 创建人：cmaple
 * 创建日期：2019-12-30
 */
@RestController
@RequestMapping("/JobApplication")
public class JobApplicationController {
    /**
     * 引入JobApplicationService
     */
    @Autowired
    private JobApplicationService jobApplicationService;
    /**
     * 引入UserService
     */
    @Autowired
    private UserService userService;


}
