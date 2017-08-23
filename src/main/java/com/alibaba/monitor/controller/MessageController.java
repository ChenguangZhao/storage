package com.alibaba.monitor.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.monitor.annotation.BussinessLog;
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

    @BussinessLog(value = "发送")
    @RequestMapping("/send")
    public Map<String, Object> send(String message) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            logger.info(message);
            logger.error(message);
            sender.send(message);
            map.put("success", true);

        } catch (InterruptedException e) {
            e.printStackTrace();
            map.put("success", false);

        }

        return map;
    }
}
