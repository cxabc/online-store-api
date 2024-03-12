package com.maidao.edu.store.common.authority;


import com.maidao.edu.store.api.admin.authority.AdminPermission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1. java反射（Reflection）是指在程序运行时动态地获取类的信息（如类名、方法、字段等）并且可以使用这些信息进行操作的机制
 *
 * 2. @Target({ElementType.METHOD}) 是 Java 中的一个元注解，用于指定注解可以应用的目标元素类型。在这个例子中，@Target({ElementType.METHOD})
 * 表示该注解只能应用在方法上。
 * 具体而言，@Target 注解可以指定以下几种目标元素类型（ElementType）：
 * ElementType.ANNOTATION_TYPE：注解类型声明。
 * ElementType.CONSTRUCTOR：构造方法声明。
 * ElementType.FIELD：字段声明（包括枚举常量）。
 * ElementType.LOCAL_VARIABLE：局部变量声明。
 * ElementType.METHOD：方法声明。
 * ElementType.PACKAGE：包声明。
 * ElementType.PARAMETER：方法参数声明。
 * ElementType.TYPE：类、接口或枚举声明。
 *
 * 3. @Retention(value = RetentionPolicy.RUNTIME) 是 Java 中的一个元注解，用于指定注解的保留策略。
 * 在这个例子中，@Retention(value = RetentionPolicy.RUNTIME) 表示该注解在运行时可通过反射获取。
 * 具体而言，@Retention 注解可以指定以下三种保留策略（RetentionPolicy）：
 * RetentionPolicy.SOURCE：注解只在源代码级别保留，编译器将会丢弃这种类型的注解，不会包含在编译后的 class 文件中。
 * RetentionPolicy.CLASS：注解在编译时保留，会包含在编译后的 class 文件中，但是在运行时无法通过反射获取。
 * RetentionPolicy.RUNTIME：注解在运行时保留，会包含在编译后的 class 文件中，并且在运行时可以通过反射获取。
 *
 * 4. 通过 @interface 关键字，可以定义一个自定义的注解类型。定义注解类型时，可以在注解中定义一些成员变量，
 * 这些成员变量可以包含默认值，也可以不包含默认值。注解中的成员变量称为注解的元素
 */
@Target({ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RequiredPermission {

    AdminType[] adminType();
// kjghiughui
    AdminPermission[] adminPermission() default AdminPermission.NONE;

}
