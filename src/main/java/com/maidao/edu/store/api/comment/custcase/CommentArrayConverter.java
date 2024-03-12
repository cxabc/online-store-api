package com.maidao.edu.store.api.comment.custcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.store.api.comment.entity.CommentItems;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author: chen.star
 * @date: 2024/3/11 17:46
 * @description: null
 **/
/*
 * @Converter(autoApply = true) 注解中的 autoApply 属性用于指定该转换器是否自动应用于所有实体属性。
 * 当 autoApply 属性设置为 true 时，该转换器将自动应用于应用程序中的所有实体属性，而无需在实体类的属性上显式指定 @Convert 注解。
 * 如果不设置 autoApply 属性或者将其设置为 false，则需要在实体类的属性上使用 @Convert 注解来指定要应用的转换器。
 */
@Converter(autoApply = true)
public class CommentArrayConverter implements AttributeConverter<CommentItems, String> {

    @Override
    public String convertToDatabaseColumn(CommentItems obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public CommentItems convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseObject(data, CommentItems.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
