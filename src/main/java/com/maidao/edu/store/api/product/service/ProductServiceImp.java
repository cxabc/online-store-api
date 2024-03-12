package com.maidao.edu.store.api.product.service;

import com.maidao.edu.store.api.product.model.Product;
import com.maidao.edu.store.api.product.qo.ProductQo;
import com.maidao.edu.store.api.product.repository.ProductRepository;
import com.maidao.edu.store.api.sort.service.SortService;
import com.maidao.edu.store.common.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: chen.star
 * @date: 2024/3/11 11:25
 * @description: null
 **/
@Service
public class ProductServiceImp implements ProductService {

    @Resource
    private ProductRepository productRepository;

    /*
     * 在Spring容器中，当一个接口只有一个实现类时，Spring能够识别并自动注入该实现类。
     *
     * 因此，虽然注入的是接口类型 SortService，但实际上被注入的是实现了 SortService 接口的 SortServiceImpl 类的实例
     */
    @Resource
    private SortService sortService;

    @Override
    public Product product(int id) {
        return productRepository.getOne(id);
    }

    @Override
    public void save(Product product) throws ServiceException {
        productRepository.save(product);
    }

    @Override
    public void remove(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void outSome(List<Integer> ids) {
        for (Integer id : ids) {
            Product exist = productRepository.getOne(id);
            exist.setStatus(2);
            productRepository.save(exist);
        }
    }

    @Override
    public void putSome(List<Integer> ids) {
        for (Integer id : ids) {
            Product exist = productRepository.getOne(id);
            exist.setStatus(1);
            productRepository.save(exist);
        }
    }

    @Override
    public Page<Product> products(ProductQo qo, boolean admin) {
        if (!admin) {
            qo.setStatus(1);
        }
        sortService.codes2Ids(qo);
        return productRepository.findAll(qo);
    }

    @Override
    public Page<Product> homeProducts(ProductQo qo) {

        if (qo.getFirstSortId() != null) {
            sortService.firstSortId2Ids(qo);
        }

        return productRepository.findAll(qo);
    }

    @Override
    public List<Product> findByIds(List<Integer> ids) {

        return productRepository.findAllByIdIn(ids);
    }

    @Override
    public List<Product> findByCodes(List<String> codes) {
        return productRepository.findAllBySortIdIn(codes);
    }
}
