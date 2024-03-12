package com.maidao.edu.store.api.user.qo;

import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.store.common.reposiotry.support.QueryField;
import com.maidao.edu.store.common.reposiotry.support.QueryType;

public class UserQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.FULL_LIKE, name = "nick")
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
