package com.maidao.edu.store.api.weapon.service;

import com.maidao.edu.store.api.weapon.model.Weapon;
import com.maidao.edu.store.api.weapon.qo.WeaponQo;
import com.maidao.edu.store.api.weapon.repository.IWeaponRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WeaponService implements IWeaponService {

    @Resource
    private IWeaponRepository iweaponRepository;

    @Override
    public List<Weapon> getAll() {
        return iweaponRepository.findAll();
    }

    @Override
    public Weapon getById(Integer id) {
        return iweaponRepository.getOne(id);
    }

    @Override
    public void saveWeapon(Weapon weapon) {
        iweaponRepository.save(weapon);
    }

    @Override
    public void deleteWeapon(Integer id) {
        iweaponRepository.deleteById(id);
    }

    @Override
    public Page<Weapon> weapons(WeaponQo weaponQo, Boolean weapon) {
         return iweaponRepository.findAll(weaponQo);
    }

}
