package com.alibaba.monitor;

/**
 * 消费者类Consumer继承线程类Thread
 *
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/18
 */
public class Consumer extends Thread {

    /**
     * 每次消费的产品数量
     */
    private int num;

    /**
     * 所在放置的仓库
     */
    private Storage storage;

    /**
     * 构造函数，设置仓库
     *
     * @param storage
     */
    public Consumer(Storage storage) {
        this.storage = storage;
    }

    /**
     * 线程run函数
     */
    @Override
    public void run() {
        storage.consume(num);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
