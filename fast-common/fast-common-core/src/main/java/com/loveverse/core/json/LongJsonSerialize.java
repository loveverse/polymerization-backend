package com.loveverse.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author love
 * @since 2025/5/21 9:05
 */
//继承JsonSerializer
public class LongJsonSerialize extends JsonSerializer<Long> {

    //重写serialize方法
    @Override
    public void serialize(Long data, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (data == null) {
            return;
        }
        //自定义处理方式
        jsonGenerator.writeNumber(data);
    }
}
