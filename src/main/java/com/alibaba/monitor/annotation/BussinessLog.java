package com.alibaba.monitor.annotation;

import java.lang.annotation.Inherited;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/22
 */
@Inherited
public @interface BussinessLog {

    /**
     * 业务的名称,例如:"修改菜单"
     */
    String value() default "";


}
