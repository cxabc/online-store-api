package com.maidao.edu.store.api.weapon.service;

import com.maidao.edu.store.api.weapon.model.Weapon;
import com.maidao.edu.store.api.weapon.qo.WeaponQo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IWeaponService {

    List<Weapon> getAll();

    Weapon getById(Integer id);//R

    void saveWeapon(Weapon weapon);//C and U

    void deleteWeapon(Integer id);//D

    Page<Weapon> weapons(WeaponQo weaponQo, Boolean weapon);

}
