package com.am.chat.bi.comm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author wpl
 */
@Data
@ApiModel
public class Result<T> {

    @ApiModelProperty(value = "元数据", required = true)
    private ResultMeta meta;

    @ApiModelProperty(value = "返回结果")
    private T response;

    public Result() {}

    private Result(ErrorCode errorCode) {
        this.meta = new ResultMeta(errorCode);
    }

    public static <T> Result<T> success() {
        return new Result<>(ErrorCode.SUCCESS);
    }

    public static <T> Result<T> fail(ErrorCode errorCode) {
        return new Result<>(errorCode);
    }

    public static boolean isSuccess(Result result) {
        return result != null && result.meta != null && result.meta.getCode() == 0;
    }

    public Result<T> withErrorMsg(String msg) {
        this.meta.setMsg(msg);
        return this;
    }

    public Result<T> withResponse(T response) {
        this.response = response;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
