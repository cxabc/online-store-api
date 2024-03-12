package com.maidao.edu.store.api.admin.model;

import com.maidao.edu.store.common.authority.Permission;
import com.maidao.edu.store.common.converter.StringArrayConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    /**
     * Convert(converter = StringArrayConverter.class) 是 JPA（Java Persistence API）中的一个注解，用于指定属性在持久化到数据库时需要使用的转换器
     * 指定了 StringArrayConverter.class 作为转换器类，表示需要将该属性转换为数据库中的特定类型
     */
    @Convert(converter = StringArrayConverter.class)
    @Column(name = "permissions")
    private List<String> permissions;

    /**
     * Transient 是 JPA（Java Persistence API）中的一个注解，用于标记实体类中的属性，表示该属性不需要持久化到数据库中。
     * 具体来说，被 @Transient 注解标记的属性将被 JPA 忽略，不会被映射为数据库表中的列。这意味着该属性的值不会保存到数据库中，也不会从数据库中加载
     */
    @Transient
    private List<Permission> pstr;

    public Role() {
    }

    public List<Permission> getPstr() {
        return pstr;
    }

    public void setPstr(List<Permission> pstr) {
        this.pstr = pstr;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

}