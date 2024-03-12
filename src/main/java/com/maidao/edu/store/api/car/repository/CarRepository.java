package com.maidao.edu.store.api.car.repository;

import com.maidao.edu.store.api.car.model.Car;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 10:24
 * @description: null
 **/
public interface CarRepository extends BaseRepository<Car, Integer> {

    List<Car> findByUserId(Integer userId);

    List<Car> findAllByIdIn(List<Integer> ids);

    Car findByUserIdAndProductIdAndSno(Integer userId, Integer productId, Integer sno);
}
