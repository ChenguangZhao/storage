package com.alibaba.monitor.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/18
 */
public class Queue {

    public final static int MAX_SIZE = 10;

    public final static LinkedBlockingQueue<Object> SMS_QUEUE = new LinkedBlockingQueue<>(
        MAX_SIZE);


}
