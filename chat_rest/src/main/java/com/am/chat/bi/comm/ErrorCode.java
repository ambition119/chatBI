package com.am.chat.bi.comm;

/**
 * @author wpl
 */
public enum ErrorCode {
    SUCCESS(1000, "success"),
    FAIL(1010, "fail"),
    FAIL_LLMs(1020, "fail LLMs"),
    FAIL_INSERT(1001, "fail insert"),
    FAIL_UPDATE(1002, "fail update"),
    FAIL_SELECT(1003, "fail select"),
    FAIL_UPLOAD(1004, "fail upload"),
    FAIL_EXPORT(1005, "fail export"),
    FAIL_DEL(1006, "fail delete"),
    FAIL_RUN_TASK(1007, "fail run task"),
    ;

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
