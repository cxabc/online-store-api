package com.maidao.edu.store.api.user.repository;

import com.maidao.edu.store.api.user.model.UserSession;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

/**
 * @author: jc.cp
 * @date: 2024/2/25 17:58
 * @description: TODO
 **/
public interface UserSessionRepository extends BaseRepository<UserSession, Integer> {
    UserSession findByToken(String token);
}