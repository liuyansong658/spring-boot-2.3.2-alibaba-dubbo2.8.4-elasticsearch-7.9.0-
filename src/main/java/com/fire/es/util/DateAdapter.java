package com.fire.es.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * liuyansong
 * 2020-11-13
 * es 转换时间的工具类
 */
public class DateAdapter implements JsonDeserializer<Date> {
    private final DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH);

    public Date deserialize(JsonElement arg0, Type arg1,
                            JsonDeserializationContext arg2) throws JsonParseException {
        try {
            return df.parse(arg0.getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
