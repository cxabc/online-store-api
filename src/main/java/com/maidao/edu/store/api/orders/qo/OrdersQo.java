package com.maidao.edu.store.api.orders.qo;

import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.store.common.reposiotry.support.QueryField;
import com.maidao.edu.store.common.reposiotry.support.QueryType;

/**
 * @author: chen.star
 * @date: 2024/3/11 19:14
 * @description: null
 **/
public class OrdersQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == 0 ? null : status;
    }
}
