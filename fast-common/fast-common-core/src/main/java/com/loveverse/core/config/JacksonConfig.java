package com.loveverse.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

/**
 * @author love
 * @since 2025/4/10
 */
@Configuration
public class JacksonConfig {
    //@Bean
    //@Primary
    //@ConditionalOnMissingBean(ObjectMapper.class)
    //public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
    //    ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    //    SimpleModule simpleModule = new SimpleModule();
    //    // 将Long类型序列化为String类型
    //    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    //    objectMapper.registerModule(simpleModule);
    //    return objectMapper;
    //}
    @Bean
    @ConditionalOnMissingBean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, ToStringSerializer.instance);

            jacksonObjectMapperBuilder.timeZone("GMT+8");
            jacksonObjectMapperBuilder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jacksonObjectMapperBuilder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        };
    }
}
