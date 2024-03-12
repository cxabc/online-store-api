package com.maidao.edu.store.api.coupon.qo;

import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.store.common.reposiotry.support.QueryField;
import com.maidao.edu.store.common.reposiotry.support.QueryType;

/**
 * @author: chen.star
 * @date: 2024/3/11 20:33
 * @description: null
 **/
public class CouponQo extends DataQueryObjectSort {
    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status;

    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;

    @QueryField(type = QueryType.EQUAL, name = "code")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
