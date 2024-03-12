package com.maidao.edu.store.api.product.repository;

import com.maidao.edu.store.api.product.model.Product;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 11:23
 * @description: null
 **/
public interface ProductRepository extends BaseRepository<Product, Integer> {

    List<Product> findAllByIdIn(List<Integer> ids);

    List<Product> findAllBySortIdIn(List<String> codes);

}
