package com.maidao.edu.store.api.weapon.qo;

import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.store.common.reposiotry.support.QueryField;
import com.maidao.edu.store.common.reposiotry.support.QueryType;

public class WeaponQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status;

    @QueryField(type = QueryType.EQUAL, name = "price")
    private Integer price;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
