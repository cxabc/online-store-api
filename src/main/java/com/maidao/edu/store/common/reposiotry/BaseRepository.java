package com.maidao.edu.store.common.reposiotry;

import com.maidao.edu.store.common.reposiotry.support.DataQueryObject;
import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.store.common.reposiotry.support.DataQueryObjectSort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
/*
 * 1.当一个接口被标注为 @NoRepositoryBean 时，Spring Data 将不会为其创建实例，也不会对其进行扫描和代理。
 * 这样可以避免在应用程序上下文中出现不必要的 Repository Bean，同时允许其他 Repository 接口继承该接口并继承其方法定义
 *
 * 2.@Transactional用于声明事务的属性,
 * readOnly 属性：指定事务是否为只读事务
 * rollbackFor 属性：指定在哪些异常情况下回滚事务
 *
 * 3.通过 JpaSpecificationExecutor 接口，我们可以在不编写 SQL 语句的情况下，通过方法调用的方式构建复杂的查询条件，并执行查询操作
 * JpaSpecificationExecutor 是 Spring Data JPA 提供的一个接口，用于支持动态查询（Dynamic Query）功能
 * JpaSpecificationExecutor 接口定义了一个名为 findAll(Specification<T> spec) 的方法，该方法接收一个 Specification 对象作为参数，用于描述查询条件
 */
@NoRepositoryBean
@Transactional(readOnly = true, rollbackFor = Exception.class)// 规范Specification
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    List<T> findAll(DataQueryObject query);

    Page<T> findAll(DataQueryObject query, Pageable page);

    Page<T> findAll(DataQueryObjectPage dataQueryObjectpage);

    List<T> findAll(DataQueryObject dataQueryObject, Sort sort);

    List<T> findAll(DataQueryObjectSort dataQueryObjectSort);
}