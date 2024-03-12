package com.maidao.edu;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 1. @SpringBootApplication 是一个组合注解，它结合了多个常用的 Spring Boot 注解，以简化 Spring Boot 应用程序的配置。
 * 它包含了以下三个注解的功能：
 * - @Configuration: 表示该类是一个配置类，其中可以定义 Bean。
 * - @EnableAutoConfiguration: 启用 Spring Boot 的自动配置机制，它会根据项目的依赖和配置自动配置 Spring 应用程序。
 * - @ComponentScan: 开启组件扫描，自动扫描并注册标有 @Component 注解（包括 @Service、@Repository 等）的组件。
 * 通过在主类上添加 @SpringBootApplication 注解，你可以简化 Spring Boot 应用程序的配置，Spring Boot 将会自动扫描组件并进行自动配置，从而加快应用程序的开发和部署过程
 *
 * 2. @EnableScheduling 是 Spring Framework 中的一个注解，用于启用基于注解的定时任务调度功能。
 * 当你在一个 Spring Boot 应用程序中使用 @EnableScheduling 注解时，Spring 将会自动扫描并启用带有 @Scheduled 注解的方法，
 * 这些方法将会在指定的时间间隔或者固定的时间点被调用
 */
@SpringBootApplication
@EnableScheduling
public class Store {

    public static void main(String[] args) {
        SpringApplication.run(Store.class, args);
    }

    public static String[] getScanPackages() {
        return Store.class.getAnnotation(ComponentScan.class).value();
    }

    /**
     * Reflections 是一个 Java 库，用于在运行时获取类的信息、调用类的方法、访问类的字段等。
     * 它提供了一种在程序运行时检查、获取和操作类的机制，被称为反射
     */
    public static Reflections getAppReflection() {
        return new Reflections(new ConfigurationBuilder().forPackages(getScanPackages()));
    }
}
