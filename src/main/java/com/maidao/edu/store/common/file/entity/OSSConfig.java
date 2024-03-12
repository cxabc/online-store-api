package com.maidao.edu.store.common.file.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * ConfigurationProperties(prefix = "oss", ignoreUnknownFields = false):
 *
 * ConfigurationProperties 注解用于绑定配置属性到一个 Java Bean 上。
 * prefix = "oss" 表示该 Bean 只会绑定以 oss 作为前缀的配置属性。
 * ignoreUnknownFields = false 表示如果在配置文件中发现了未知的字段，将抛出异常，避免在读取配置时忽略未知的属性。
 * Component:
 *
 * Component 是 Spring 框架中用于标识一个类为 Spring 管理的组件的注解。在这里，它将被用于标识一个普通的 Java 类，告诉 Spring 框架需要对其进行扫描和实例化。
 * Validated:
 *
 * Validated 注解是 Spring Validation 框架的一部分，用于启用方法级别的参数验证。当你在类中使用了 @Validated 注解，
 * Spring 将会在调用该类的方法之前，对方法的参数进行验证。
 */
@ConfigurationProperties(prefix = "oss", ignoreUnknownFields = false)
@Component
@Validated
public class OSSConfig {

    /**
     * NotNull 注解通常与 @Validated 注解一起使用，用于在 Spring Bean 的属性、方法参数或方法返回值上执行参数验证,用于确保一个对象引用不为 null
     */
    @NotNull
//    @Value("${oss.key}")
    private String key;
    @NotNull
    private String secret;
    @NotNull
    private String bucket;
    @NotNull
    private String internalEndpoint;
    @NotNull
    private String canonicalDomain;
    @NotNull
    private String cdnDomain;
    @NotNull
    private String namespace;
    @NotNull
    private String putArn;
    @NotNull
    private String region;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getInternalEndpoint() {
        return internalEndpoint;
    }

    public void setInternalEndpoint(String internalEndpoint) {
        this.internalEndpoint = internalEndpoint;
    }

    public String getCanonicalDomain() {
        return canonicalDomain;
    }

    public void setCanonicalDomain(String canonicalDomain) {
        this.canonicalDomain = canonicalDomain;
    }

    public String getCdnDomain() {
        return cdnDomain;
    }

    public void setCdnDomain(String cdnDomain) {
        this.cdnDomain = cdnDomain;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getPutArn() {
        return putArn;
    }

    public void setPutArn(String putArn) {
        this.putArn = putArn;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
