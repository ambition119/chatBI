package com.am.chat.bi.comm;

/**
 * @author wpl
 */
public enum StateCode {
    IS_ADD(0),
    IS_UPDATE(1),
    IS_DEL(2)
    ;

    private Integer code;
    StateCode(Integer code) {
        this.code = code;
    }

    public static StateCode getStateCode(Integer code) {
        switch (code){
            case 1:
                return IS_UPDATE;
            case 2:
                return IS_DEL;
            default:
                return IS_ADD;
        }
    }

    public Integer getCode() {
        return code;
    }
}
