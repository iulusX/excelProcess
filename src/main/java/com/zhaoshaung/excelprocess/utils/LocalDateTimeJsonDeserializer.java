package com.zhaoshaung.excelprocess.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Strings;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author xiaoyunfeng
 * @date 2019-06-27
 * @time 21:48
 * @description
 */
public class LocalDateTimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {

    public LocalDateTimeJsonDeserializer() {
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return !Objects.isNull(jsonParser) && !Strings.isNullOrEmpty(jsonParser.getText()) ? LocalDateTime.parse(jsonParser.getText(), Consts.DATE_TIME_FORMATTER) : null;

    }
}
