package com.alibaba.monitor.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.monitor.result.AjaxResult;

import com.google.common.base.Strings;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/22
 */
public class FastJsonpHttpMessageConverter extends FastJsonHttpMessageConverter {

    public static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    protected boolean supports(Class<?> clazz) {
        return AjaxResult.class == clazz;
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException,
        HttpMessageNotWritableException {

        AjaxResult ajaxResult = (AjaxResult) obj;
        if (Strings.isNullOrEmpty(ajaxResult.getCallback())) {
            super.writeInternal(ajaxResult.getJsonObject(), outputMessage);
            return;
        }
        OutputStream out = outputMessage.getBody();
        byte[] bytes;
        if (this.getCharset() == UTF8) {
            byte[] jsonBytes;
            if (this.getFeatures() != null) {
                jsonBytes = JSON.toJSONBytes(ajaxResult.getJsonObject(), this.getFeatures());
            } else {
                jsonBytes = JSON.toJSONBytes(ajaxResult.getJsonObject(), new SerializerFeature[0]);
            }
            bytes = compile(ajaxResult.getCallback(), jsonBytes);
        } else {
            String text;
            if (this.getFeatures() != null) {
                text =
                    ajaxResult.getCallback() + "(" + JSON.toJSONString(ajaxResult.getJsonObject(), getFeatures())
                        + ");";
            } else {
                text = ajaxResult.getCallback() + "(" + JSON.toJSONString(ajaxResult.getJsonObject()) + ");";
            }

            bytes = text.getBytes(this.getCharset());
        }

        out.write(bytes);
    }

    /**
     * 将回调函数名和两个括号以及真正数据拼装起来
     *
     * @param jsonpFunction jsonp加在返回数据开头的回头函数名称
     * @param jsonBytes 真正需要返回的json格式的数据
     * @return 最终需要发出的字节数组
     */
    private byte[] compile(String jsonpFunction, byte[] jsonBytes) {
        byte[] beginList = (jsonpFunction + "(").getBytes(UTF8);
        byte[] endList = ");".getBytes(UTF8);
        byte[] res = new byte[beginList.length + jsonBytes.length + endList.length];
        System.arraycopy(beginList, 0, res, 0, beginList.length);
        System.arraycopy(jsonBytes, 0, res, beginList.length, jsonBytes.length);
        System.arraycopy(endList, 0, res, beginList.length + jsonBytes.length, endList.length);
        return res;
    }


}
