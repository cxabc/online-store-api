package com.maidao.edu.store.api.orders.converter;

import com.alibaba.fastjson.JSON;
import com.maidao.edu.store.api.address.model.Address;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author: chen.star
 * @date: 2024/3/11 18:02
 * @description: null
 **/
@Converter(autoApply = true)
public class AddressOrdersArrayConverter implements AttributeConverter<Address, String> {
    @Override
    public String convertToDatabaseColumn(Address obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public Address convertToEntityAttribute(String data) {
        try {
            return JSON.parseObject(data, Address.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
