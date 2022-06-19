package com.dabing.wiki.resp;

import lombok.Data;

@Data
public class CommonResp<T> {
    //业务是否成功
    //返回信息
    //返回数据

    private boolean success=true; //默认成功
    private String message;//是失败返回信息
    private T date; //成功返回数据

    public CommonResp(boolean success, String message, T date) {
        this.success = success;
        this.message = message;
        this.date = date;
    }

    public CommonResp() {
    }

}
