package com.quick.start.configuration.test;

import com.quick.start.configuration.config.OrderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderPropertiesCommandLineRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private OrderProperties orderProperties;

    @Override
    public void run(String... args) throws Exception {
        logger.info("OrderPropertiesCommandLineRunner-payTimeoutSeconds:"+orderProperties.getPayTimeoutSeconds());
        logger.info("OrderPropertiesCommandLineRunner-createFrequencySeconds:"+orderProperties.getCreateFrequencySeconds());
        logger.info("OrderPropertiesCommandLineRunner-desc:"+orderProperties.getDesc());
    }
}
