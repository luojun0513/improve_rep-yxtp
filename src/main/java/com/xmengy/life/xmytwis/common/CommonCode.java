package com.xmengy.life.xmytwis.common;

public enum CommonCode implements EnumCode {

    SUCCESS_CODE(1000, "成功"),
    ERROR_CODE(9999,"异常"),
    PARAM_ERROR(1001,"参数完整性错误"),
    PK_ERROR(1002,"违反主键约束"),
    ORDER_CLOSE(1003,"订单已完成，不能重复提交！"),
    ORDER_NULL(1004,"没有符合条件的记录！"),
    ORDER_AMOUT_ERROR(1005,"订单金额不对！"),
    
    USER_NOT_EXITS(1006,"用户不存在"),
    USER_ERROR_PASSWORD(1007,"密码密码不符");
    
    private Integer code;

    private String msg;

    private CommonCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
