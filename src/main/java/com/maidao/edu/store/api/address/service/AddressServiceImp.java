package com.maidao.edu.store.api.address.service;

import com.maidao.edu.store.api.address.model.Address;
import com.maidao.edu.store.api.address.qo.AddressQo;
import com.maidao.edu.store.api.address.repository.AddressRepository;
import com.maidao.edu.store.common.context.Contexts;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author: chen.star
 * @date: 2024/3/9 14:03
 * @description: null
 **/
@Service
public class AddressServiceImp implements AddressService{

    @Resource
    private AddressRepository addressRepository;

    @Override
    public List<Address> addresss(AddressQo qo, boolean admin) {
        qo.setUserId(Contexts.requestUserId());
        return addressRepository.findAll(qo);
    }

    @Override
    public Address address(int id) {
//        if (addressRepository.findById(id).isPresent()) {
//            return addressRepository.findById(id).get();
//        }
//        return null;
        /*
         * .orElse(null)方法可以达到.get()方法的效果，将Optional对象包装的值提取出来。
         * 如果Optional对象为空，则.orElse(null)会返回null，与.get()类似
         */
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Address address) throws ServiceException {
        Integer userId = Contexts.requestUserId();
        address.setUserId(userId);
        addressRepository.save(address);
    }

    @Override
    public void remove(int id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void def(Integer id) throws Exception {

        List<Address> addresses = addressRepository.findByUserId(Contexts.requestUserId());
//        addresses.stream()
//                .filter(Objects::isNull).filter(obj -> false)
//                .forEach(a -> {
//                    a.setDef(2);
//                    addressRepository.save(a);
//                });
        /*
         * forEach里面不能进行添加和删除操作,将会触发 ConcurrentModificationException 异常
         */
        for (Address a : addresses) {
            if (a != null) {
                a.setDef(2);
                addressRepository.save(a);
            }
        }
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address exist = optionalAddress.get();
            exist.setDef(1);
            addressRepository.save(exist);
            // 处理已存在的地址
        }

    }
}
