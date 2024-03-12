package com.maidao.edu.store.api.car.service;

import com.maidao.edu.store.api.car.model.Car;
import com.maidao.edu.store.common.exception.ServiceException;

import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 10:27
 * @description: null
 **/
public interface CarService {

    List<Car> cars() throws Exception;

    Car car(int id);

    Car save(Car car) throws ServiceException;

    void remove(int id);

    List<Car> findByIds(List<Integer> ids);


}
