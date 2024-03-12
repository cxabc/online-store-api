package com.maidao.edu.store.api.user.model;

import com.maidao.edu.store.common.context.SessionWrap;

public class UserSessionWrap implements SessionWrap {

    private User user;
    private UserSession userSession;

    public UserSessionWrap(User user, UserSession userSession) {
        this.user = user;
        this.userSession = userSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}