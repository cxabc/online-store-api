package com.maidao.edu.store.api.admin.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    /**
     * @Id 属性被标识为主键，并且指定了 GenerationType.IDENTITY 作为主键生成策略，表示该主键由数据库自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "name")
    private String name;

    /**
     * @JSONField(serialize = false) 是阿里巴巴 FastJSON（一个 Java 的 JSON 处理库）中的一个注解，用于控制在对象序列化为 JSON 字符串时是否包含被注解标记的字段。
     * 具体来说，serialize = false 表示在将对象序列化为 JSON 字符串时，不包含被注解标记的字段。换句话说，注解标记的字段不会出现在生成的 JSON 字符串中。
     */
    @JSONField(serialize = false)
    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "signin_at")
    private Long signinAt;

    /**
     * @Transient 注解可以用于标记一个属性，告诉Hibernate不要将该属性持久化到数据库中
     */
    @Transient
    private Role role;
    @Transient
    private String newpassword;

    public Admin() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSigninAt() {
        return signinAt;
    }

    public void setSigninAt(Long signinAt) {
        this.signinAt = signinAt;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

}