package com.zhaoshaung.excelprocess.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author xiaoyunfeng
 * @date 2019-06-27
 * @time 21:45
 * @description
 */
public class LocalDateTimeJsonSerializable extends JsonSerializer<LocalDateTime> {

    public LocalDateTimeJsonSerializable() {
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(Consts.DATE_TIME_FORMATTER.format(localDateTime));
    }
}
