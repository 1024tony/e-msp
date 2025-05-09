package com.volvo.emsp.common.model;

import lombok.*;

/**
 * 通用分页请求
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
public class PageRequest extends Request {

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 页大小
     */
    private Integer pageSize = 10;
}
