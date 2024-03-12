package com.maidao.edu.store.api.user.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * 1. @ConfigurationProperties 注解用于将配置文件中的属性值绑定到一个 Java Bean 上。
 * 在这个例子中，prefix = "user" 指定了配置属性的前缀为 "user"，而 ignoreInvalidFields = false 则表示如果在配置文件中发现了未知的字段，将抛出异常而不是忽略未知的属性.
 */
@Component
@ConfigurationProperties(prefix = "user", ignoreInvalidFields = true)
public class UserConfig {
    private String salt;

    private Integer sessionDays;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getSessionDays() {
        return sessionDays;
    }

    public void setSessionDays(Integer sessionDays) {
        this.sessionDays = sessionDays;
    }
}
