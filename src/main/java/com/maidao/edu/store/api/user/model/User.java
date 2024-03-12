package com.maidao.edu.store.api.user.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.maidao.edu.store.api.vip.model.VipUser;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author: jc.cp
 * @date: 2024/2/25 17:41
 * @description: TODO
 **/

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String mobile;

    @Column
    private String email;

    @Column
    private String nick;

    @Column
    @JSONField(serialize = false)
    private String password;

    @Column(name = "created_at")
    private long createdAt;

    @Column
    private String img;

    /*
     * BigDecimal 是 Java 中用于表示任意精度的十进制数的类。
     * 它位于 java.math 包中，用于表示非常大或非常小的数值，以及需要精确计算的数值
     */
    @Column
    private BigDecimal balance;

    @Transient
    private VipUser vipUser;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public VipUser getVipUser() {
        return vipUser;
    }

    public void setVipUser(VipUser vipUser) {
        this.vipUser = vipUser;
    }
}
