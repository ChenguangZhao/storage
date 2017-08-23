package com.alibaba.monitor.service.impl;

import java.util.concurrent.TimeUnit;

import com.alibaba.monitor.queue.Queue;
import com.alibaba.monitor.service.Sender;

import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/18
 */
@Service
public class SmsSender implements Sender {

    @Override
    public void send(String message) throws InterruptedException {
        if (Queue.SMS_QUEUE.offer(message,1, TimeUnit.SECONDS)) {
            System.out.println(message);
            System.out.println("push done");
        } else {
            System.out.println("queue is full");
        }
    }
}
