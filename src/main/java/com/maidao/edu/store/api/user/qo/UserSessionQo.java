package com.maidao.edu.store.api.user.qo;

import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.store.common.reposiotry.support.QueryField;
import com.maidao.edu.store.common.reposiotry.support.QueryType;

/**
 * @author: jc.cp
 * @date: 2024/2/25 18:03
 * @description: TODO
 **/
public class UserSessionQo extends DataQueryObjectPage {
    @QueryField(type = QueryType.EQUAL, name = "userId")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
