package com.quick.start.securityframework.common.util;

import java.io.Serializable;

/**
 * 分页
 */
public class PageTableRequest implements Serializable {

    private Integer page;
    private Integer limit;
    private Integer offset;

    public void countOffset() {
        if (null == this.page || null == this.limit) {
            this.offset = 0;
            return;
        }
        this.offset = (this.page - 1) * limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
