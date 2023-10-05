package com.am.chat.bi.comm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wpl
 */
@Data
@ApiModel
public class ResultMeta {

    @ApiModelProperty(value = "错误码", required = true)
    private int code;
    @ApiModelProperty(value = "描述", required = true)
    private String msg;

    public ResultMeta() {}

    public ResultMeta(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    void setMsg(String msg) {
        this.msg = this.msg + " : " + msg;
    }
}
