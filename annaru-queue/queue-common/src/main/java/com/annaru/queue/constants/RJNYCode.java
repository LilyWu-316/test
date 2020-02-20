package com.annaru.queue.constants;

/* 仁济南院请求码
* @Author: jyehui
* @Date: 2019/10/21 11:08
*/
public enum RJNYCode {
    /**
     * 0请求成功
     */
    OK(0),
    /**
     * 10数据校验失败
     */
    DATA_CHECK(10),
    /**
     * 90业务错误
     */
    SERVICE_ERROR(90),
    /**
     * 99认证失败
     */
    AUTH_FAILURE(99),
    /**
     * 100药柜爆满
     */
    NO_VACANCY(100),
    /**
     * 500订单下发失败
     */
    BAD(500),
    /**
     * 101订单已存在
     */
    ALREADY_EXIST(101);

    private final Integer value;

    private RJNYCode(Integer value) {
        this.value = value;
    }


    public Integer value() {
        return this.value;
    }

    public String toString() {
        return this.value.toString();
    }
}
