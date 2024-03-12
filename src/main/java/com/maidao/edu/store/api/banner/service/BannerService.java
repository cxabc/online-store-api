package com.maidao.edu.store.api.banner.service;

import com.maidao.edu.store.api.banner.model.Banner;
import com.maidao.edu.store.api.banner.qo.BannerQo;

import java.util.List;

/**
 **/
public interface BannerService {

    List<Banner> banners(BannerQo bannerQo, boolean admin);

    Banner banner(int id);

    void save(Banner banner) throws Exception;

    void remove(int id) throws Exception;

    void outSome(List<Integer> ids);

    void putSome(List<Integer> ids);
}
