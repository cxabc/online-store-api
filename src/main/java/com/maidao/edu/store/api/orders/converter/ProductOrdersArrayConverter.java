package com.maidao.edu.store.api.orders.converter;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.car.model.Car;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 18:02
 * @description: null
 **/
@Converter(autoApply = true)
public class ProductOrdersArrayConverter implements AttributeConverter<List<Car>, String> {

    @Override
    public String convertToDatabaseColumn(List<Car> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<Car> convertToEntityAttribute(String data) {
        try {
            return JSON.parseArray(data, Car.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
