package com.maidao.edu.store.common.reposiotry.support;

import com.maidao.edu.store.common.entity.Constants;
import com.sunnysuperman.commons.util.FormatUtil;

public class DataQueryObjectPage extends DataQueryObjectSort {

    protected Integer pageNumber = 0;
    protected Integer pageSize = Constants.PAGESIZE_MIN_CC;

    public Integer getPageNumber() {
        int asInt = FormatUtil.parseIntValue(pageNumber, 0);
        return asInt <= 0 ? 0 : asInt - 1;
    }

    public void setPageNumber(Integer page) {
        this.pageNumber = page;
    }

    public Integer getPageSize() {
        Integer defaultValue = Constants.PAGESIZE_MIN;
        Integer maxValue = Constants.PAGESIZE_MAX;
        if (pageSize == null) {
            return defaultValue;
        }
        if (pageSize <= 0 || pageSize > maxValue) {
            pageSize = (defaultValue > maxValue) ? maxValue : defaultValue;
        }
        return pageSize;
    }

    public void setPageSize(Integer size) {
        this.pageSize = size;
    }
}
