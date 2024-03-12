package com.maidao.edu.store.common.reposiotry.support;

public enum QueryType {

    EQUAL(false),
    BEWTEEN(false),//bewteen
    LESS_THAN(false),
    LESS_THAN_EQUAL(false),
    GREATEROR_THAN(false),
    GREATEROR_THAN_EQUAL(false),
    NOT_EQUAL(false),
    IS_NULL(true),
    IS_NOT_NULL(true),
    RIGHT_LIKE(false),//right like
    LEFT_LIKE(false),
    FULL_LIKE(false),
    DEFAULT_LIKE(false),
    NOT_LIKE(false),
    IN(false);

    //	是否可以为空
    private boolean isCanBeNull;

    private QueryType(boolean isCanBeNull) {
        this.isCanBeNull = isCanBeNull;
    }

    public boolean isCanBeNull() {
        return this.isCanBeNull;
    }
}
