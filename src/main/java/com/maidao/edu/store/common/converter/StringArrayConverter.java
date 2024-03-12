package com.maidao.edu.store.common.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * 在一个自定义的类型转换器类上添加了 @Converter(autoApply = true) 注解时，
 * 该转换器将会自动应用于整个持久化单元中的相应类型，而不需要额外的配置
 */
@Converter(autoApply = true)
public class StringArrayConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<String> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
