package com.maidao.edu.store.api.car.service;

import com.maidao.edu.store.api.car.model.Car;
import com.maidao.edu.store.api.car.repository.CarRepository;
import com.maidao.edu.store.api.product.repository.ProductRepository;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 10:32
 * @description: null
 **/
@Service
public class CarServiceImp implements CarService{

    @Resource
    private CarRepository carRepository;

    @Resource
    private ProductRepository productRepository;

    @Override
    public List<Car> cars() throws Exception {

        Integer userId = Contexts.requestUserId();

        List<Car> cars = carRepository.findByUserId(userId);
        cars.forEach(car -> {
            car.setProduct(productRepository.findById(car.getProductId()).orElse(null));
        });
        return cars;
    }

    @Override
    public Car car(int id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car save(Car car) throws ServiceException {
        Integer userId = Contexts.requestUserId();
        Car exist = carRepository.findByUserIdAndProductIdAndSno(userId, car.getProductId(), car.getSno());
        if (car.getId() == null) {
            if (exist == null) {
                car.setUserId(userId);
                carRepository.save(car);
                return car;
            } else {
                exist.setNum(exist.getNum() + car.getNum());
                carRepository.save(exist);
                return exist;
            }
        } else {
            if (exist == null) {
                car.setUserId(userId);
                carRepository.save(car);
                return car;
            } else {
                if (!exist.getId().equals(car.getId())) {
                    exist.setNum(exist.getNum() + car.getNum());
                    carRepository.save(exist);
                    carRepository.deleteById(car.getId());
                    return exist;
                } else {
                    exist.setNum(car.getNum());
                    carRepository.save(exist);
                    return exist;
                }
            }
        }
    }

    @Override
    public void remove(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> findByIds(List<Integer> ids) {
        List<Car> cars = carRepository.findAllByIdIn(ids);
        cars.forEach(car -> {
            car.setProduct(productRepository.findById(car.getProductId()).orElse(null));
        });
        return cars;
    }
}
