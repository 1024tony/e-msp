package com.volvo.emsp.common.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用分页结果对象
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PageResult<T> extends Result {

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 页大小
     */
    private Integer pageSize = 10;

    /**
     * 总记录数
     */
    private Integer totalCount = 0;

    /**
     * 总页数
     */
    private Integer totalPage = 0;

    /**
     * 分页列表
     */
    private List<T> list = new ArrayList<>();


    /**
     * 设置页码
     *
     * @param pageNum
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = (pageNum <= 0 ? 1 : pageNum);
    }

    /**
     * 计算总页数
     *
     * @return
     */
    public Integer getTotalPage() {
        return this.pageSize <= 0 ? 0 : (int) Math.ceil((double) this.totalCount / (double) this.pageSize);
    }

    /**
     * 是否有下一页
     *
     * @return
     */
    public Boolean isHasNext() {
        return pageNum < totalPage;
    }
}
