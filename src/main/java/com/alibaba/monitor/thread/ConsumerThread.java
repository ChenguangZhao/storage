package com.alibaba.monitor.thread;

import com.alibaba.monitor.queue.Queue;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/18
 */
public class ConsumerThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Object message = Queue.SMS_QUEUE.take();
                System.out.println("get message :" + message);
                Thread.sleep(10000);
                System.out.println("send  message success :" + message);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
