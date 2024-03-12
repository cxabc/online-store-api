package com.maidao.edu.store.api.user.repository;

import com.maidao.edu.store.api.user.model.User;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

/**
 * @author: jc.cp
 * @date: 2024/2/25 17:56
 * @description: TODO
 **/
public interface UserRepository extends BaseRepository<User, Integer> {

    User findByMobile(String mobile) throws Exception;

    User findByEmail(String email) throws Exception;

    User findByNick(String nick) throws Exception;

}
