package com.quick.start.configuration.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")
public class OrderProperties {

    /**
     * 订单支付超时时长，单位：秒。
     */
    private Integer payTimeoutSeconds;

    /**
     * 订单创建频率，单位：秒
     */
    private Integer createFrequencySeconds;

    /**
     * 配置项可以引用另外的配置项，进行拼接
     */
    private String desc;

    public Integer getPayTimeoutSeconds() {
        return payTimeoutSeconds;
    }

    public void setPayTimeoutSeconds(Integer payTimeoutSeconds) {
        this.payTimeoutSeconds = payTimeoutSeconds;
    }

    public Integer getCreateFrequencySeconds() {
        return createFrequencySeconds;
    }

    public void setCreateFrequencySeconds(Integer createFrequencySeconds) {
        this.createFrequencySeconds = createFrequencySeconds;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
