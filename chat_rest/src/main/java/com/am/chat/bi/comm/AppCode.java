package com.am.chat.bi.comm;

/**
 * @author wpl
 * 后期数据库配置
 */
public enum AppCode {
    VSKIT(1, "vskit")
    ;
    private int code;
    private String appName;

    AppCode(int code, String appName) {
        this.code = code;
        this.appName = appName;
    }

    public int getCode() {
        return code;
    }

    public String getAppName() {
        return appName;
    }

    public static String getAppName(int code) {
        switch (code){
            case 1:
            case 2:
            default:
                return VSKIT.getAppName();
        }
    }
    
}

