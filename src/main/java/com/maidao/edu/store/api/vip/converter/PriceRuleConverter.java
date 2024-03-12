package com.maidao.edu.store.api.vip.converter;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.vip.entity.PriceRule;

import javax.persistence.AttributeConverter;
import java.util.List;

/*
 * 1. AttributeConverter在持久化实体属性与数据库字段之间进行转换。它允许开发者定义自定义的类型转换器
 *
 * 一个 AttributeConverter 需要实现两个方法：
 * convertToDatabaseColumn 方法：将实体属性转换为数据库列的值。
 * convertToEntityAttribute 方法：将数据库列的值转换为实体属性。
 */
public class PriceRuleConverter implements AttributeConverter<List<PriceRule>, String> {

    @Override
    public String convertToDatabaseColumn(List<PriceRule> priceRules) {
        return JSON.toJSONString(priceRules);
    }

    @Override
    public List<PriceRule> convertToEntityAttribute(String data) {
        try {
            return JSON.parseArray(data, PriceRule.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
