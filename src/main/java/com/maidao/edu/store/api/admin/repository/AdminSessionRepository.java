package com.maidao.edu.store.api.admin.repository;


import com.maidao.edu.store.api.admin.model.AdminSession;
import com.maidao.edu.store.common.reposiotry.BaseRepository;

public interface AdminSessionRepository extends BaseRepository<AdminSession, Integer> {

    AdminSession findByToken(String token);

}