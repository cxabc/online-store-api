package com.maidao.edu.store.api.vip.repository;

import com.maidao.edu.store.api.vip.model.Vip;
import com.maidao.edu.store.common.reposiotry.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 **/
public interface VipRepository extends BaseRepository<Vip, Integer> {

    Vip findByGrade(Integer grade);

    Vip findByName(String name);

    /*
     * @Transactional: 该注解用于声明这个方法需要在事务中执行
     * @Modifying 注解通常与 @Query 注解一起使用，
     * @Query 注解用于声明查询语句，
     * @Modifying 注解用于声明修改操作。在声明修改操作的方法上，需要同时使用这两个注解。
     * 在 SQL 查询中，:status 和 :id 是参数占位符,:status 和 :id 分别代表了两个参数，分别为 status 和 id
     */
    @Transactional
    @Modifying
    @Query("update Vip set status = :status where id = :id")
    void modStatusById(Integer id, Integer status);
}
