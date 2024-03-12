package com.maidao.edu.store.api.address.repository;

import com.maidao.edu.store.api.address.model.Address;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

import java.util.List;

/**
 **/
public interface AddressRepository extends BaseRepository<Address, Integer> {
    /*
     * BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>
     * extends BaseRepository<Address, Integer>就可以用JPA的方法，根据JPA命名标准实现方法
     */
    List<Address> findByUserId(Integer userId);
}
