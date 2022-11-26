package com.quick.start.configuration.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my")
public class RandomProperties {

    /**
     * 指定 int 整数。
     */
    private Integer myNumber1;

    /**
     * 指定 long 整数。
     */
    private Long myLongNumber;

    /**
     * 随机小于 10 的 int 整数。
     */
    private Integer myNumber2;

    /**
     * 随机大于等于 10 ，小于等于 65536 的 int 整数。
     */
    private Integer myNumber3;

    /**
     * 普通字符串
     */
    private String secret1;

    /**
     * UUID字符串
     */
    private String secret2;

    public Integer getMyNumber1() {
        return myNumber1;
    }

    public void setMyNumber1(Integer myNumber1) {
        this.myNumber1 = myNumber1;
    }

    public Long getMyLongNumber() {
        return myLongNumber;
    }

    public void setMyLongNumber(Long myLongNumber) {
        this.myLongNumber = myLongNumber;
    }

    public Integer getMyNumber2() {
        return myNumber2;
    }

    public void setMyNumber2(Integer myNumber2) {
        this.myNumber2 = myNumber2;
    }

    public Integer getMyNumber3() {
        return myNumber3;
    }

    public void setMyNumber3(Integer myNumber3) {
        this.myNumber3 = myNumber3;
    }

    public String getSecret1() {
        return secret1;
    }

    public void setSecret1(String secret1) {
        this.secret1 = secret1;
    }

    public String getSecret2() {
        return secret2;
    }

    public void setSecret2(String secret2) {
        this.secret2 = secret2;
    }
}
