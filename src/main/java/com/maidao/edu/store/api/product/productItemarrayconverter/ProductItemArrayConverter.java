package com.maidao.edu.store.api.product.productItemarrayconverter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.store.api.product.entity.ProductItem;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 10:05
 * @description: 自定义转换器,决定存入数据库转换格式
 **/

/*
 * @Converter(autoApply = true) 注解中的 autoApply 属性用于指定该转换器是否自动应用于所有实体属性。
 * 当 autoApply 属性设置为 true 时，该转换器将自动应用于应用程序中的所有实体属性，而无需在实体类的属性上显式指定 @Convert 注解。
 * 如果不设置 autoApply 属性或者将其设置为 false，则需要在实体类的属性上使用 @Convert 注解来指定要应用的转换器。
 */
@Converter(autoApply = true)
public class ProductItemArrayConverter implements AttributeConverter<List<ProductItem>, String> {

    @Override
    public String convertToDatabaseColumn(List<ProductItem> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<ProductItem> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, ProductItem.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
