package com.am.chat.bi.domain.vo;

import lombok.Data;

/**
 * @author wpl
 * @param <T>
 */
@Data
public class ResponseVo<T> {
    //返回插入或者修改的主键id
    private String name;
    private T value;
}
