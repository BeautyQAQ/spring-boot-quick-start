package com.quick.start.configuration.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ValueCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ValueCommandLineRunner.class);

    @Value("${order.pay-timeout-seconds}")
    private Integer payTimeoutSeconds;

    @Value("${order.create-frequency-seconds}")
    private Integer createFrequencySeconds;

    @Value("${rpc-test}")
    private String rpcTest;

    @Value("${start-arg}")
    private String startArg;

    @Override
    public void run(String... args) throws Exception {
        logger.info("ValueCommandLineRunner-payTimeoutSeconds:"+payTimeoutSeconds);
        logger.info("ValueCommandLineRunner-createFrequencySeconds:"+payTimeoutSeconds);
        logger.info("ValueCommandLineRunner-rpcTest:"+rpcTest);
        logger.info("ValueCommandLineRunner-startArg:"+startArg);
    }
}
