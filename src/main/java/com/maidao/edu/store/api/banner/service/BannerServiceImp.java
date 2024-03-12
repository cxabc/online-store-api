package com.maidao.edu.store.api.banner.service;

import com.maidao.edu.store.api.banner.model.Banner;
import com.maidao.edu.store.api.banner.qo.BannerQo;
import com.maidao.edu.store.api.banner.repository.BannerRepository;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.maidao.edu.store.common.exception.ErrorCode.ERR_BANNER_IMG_NULL;

/**
 **/
@Service
public class BannerServiceImp implements BannerService {

    @Resource
    private BannerRepository bannerRepository;
    @Override
    public List<Banner> banners(BannerQo qo, boolean admin) {
        if (!admin) {
            qo.setStatus(1);
        }
        return bannerRepository.findAll(qo);
    }

    @Override
    public Banner banner(int id) {
        return bannerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Banner banner) throws Exception {
        if (StringUtils.isEmpty(banner.getImg())){
            throw new ServiceException(ERR_BANNER_IMG_NULL);// err.100 = 请上传图片
        }
        bannerRepository.save(banner);
    }

    @Override
    public void remove(int id) throws Exception {
        bannerRepository.deleteById(id);
    }

    @Override
    public void outSome(List<Integer> ids) {
        // 老方式 for循环
//        for (Integer id : ids) {
//            Banner exist = bannerRepository.getOne(id);
//            exist.setStatus(2);
//            bannerRepository.save(exist);
//        }
        // 推荐:用Optional和stream()流替代传统的for循环
        Optional.ofNullable(ids).orElse(new ArrayList<>())
                .stream()
                .filter(Objects::nonNull)
                .forEach(id -> {
                    // findById(id) 和 getOne(id) 方法的主要区别在于返回值的类型和加载方式。
                    // findById(id) 返回的是一个 Optional<T> 对象，立即加载；
                    // 而 getOne(id) 返回的是实体对象的代理，延迟加载
//                    Banner exist = bannerRepository.getOne(id);
                    // 要从 Optional<Banner> 中取出 Banner 对象，你可以使用 orElse(null) 方法或者 orElseThrow() 方法
                    Banner exist = bannerRepository.findById(id).orElse(null);
                    exist.setStatus(2);
                    bannerRepository.save(exist);
                });
    }

    @Override
    public void putSome(List<Integer> ids) {
        Optional.ofNullable(ids).orElse(new ArrayList<>())
                .stream()
                .filter(Objects::nonNull)
                .forEach(id -> {
                    // findById(id) 和 getOne(id) 方法的主要区别在于返回值的类型和加载方式。
                    // findById(id) 返回的是一个 Optional<T> 对象，立即加载；
                    // 而 getOne(id) 返回的是实体对象的代理，延迟加载
//                    Banner exist = bannerRepository.getOne(id);
                    // 要从 Optional<Banner> 中取出 Banner 对象，你可以使用 orElse(null) 方法或者 orElseThrow() 方法
                    Banner exist = bannerRepository.findById(id).orElse(null);
                    exist.setStatus(1);
                    bannerRepository.save(exist);
                });
    }
}
