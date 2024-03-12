package com.maidao.edu.store.common.reposiotry.support;

public class DataQueryObjectSort implements DataQueryObject {

    protected String sortPropertyName = "id";// Property属性
    protected boolean sortAscending = false;


    public String getSortPropertyName() {
        return sortPropertyName;
    }

    public void setSortPropertyName(String sortPropertyName) {
        this.sortPropertyName = sortPropertyName;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }
}
