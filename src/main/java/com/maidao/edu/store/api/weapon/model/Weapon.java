package com.maidao.edu.store.api.weapon.model;

import javax.persistence.*;

@Entity
@Table(name = "weapon")
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "img")
    private String img;

    @Column(name = "status")
    private Integer status;

    public Weapon() {

    }

    public Weapon(Integer id, String weaponName, Integer price, String img, Integer status) {
        this.id = id;
        this.weaponName = weaponName;
        this.price = price;
        this.img = img;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
