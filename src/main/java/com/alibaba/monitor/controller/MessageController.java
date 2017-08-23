package com.alibaba.monitor.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.monitor.annotation.BussinessLog;
import com.alibaba.monitor.result.AjaxResult;
import com.alibaba.monitor.service.impl.SmsSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/18
 */
@RestController
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    SmsSender sender;

    /**
     * @param message
     * @return
     */
    @BussinessLog(value = "发送")
    @RequestMapping("/send")
    public AjaxResult send(String callback, String message) {

        try {
            logger.info(message);
            logger.error(message);
            sender.send(message);
            return AjaxResult.succResult(callback);

        } catch (InterruptedException e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());

        }
    }
}
