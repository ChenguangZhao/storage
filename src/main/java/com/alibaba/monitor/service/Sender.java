package com.alibaba.monitor.service;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/18
 */
public interface Sender {

    public void send(String message) throws InterruptedException;
}
