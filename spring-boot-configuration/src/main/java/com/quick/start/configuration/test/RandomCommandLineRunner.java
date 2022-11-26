package com.quick.start.configuration.test;

import com.quick.start.configuration.config.RandomProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RandomCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RandomCommandLineRunner.class);

    @Resource
    private RandomProperties randomProperties;

    @Override
    public void run(String... args) throws Exception {
        logger.info("RandomCommandLineRunner-my_number_1:"+randomProperties.getMyNumber1());
        logger.info("RandomCommandLineRunner-my_number_2:"+randomProperties.getMyNumber2());
        logger.info("RandomCommandLineRunner-my_number_3:"+randomProperties.getMyNumber3());
        logger.info("RandomCommandLineRunner-my_long_number:"+randomProperties.getMyLongNumber());
        logger.info("RandomCommandLineRunner-Secret_1:"+randomProperties.getSecret1());
        logger.info("RandomCommandLineRunner-Secret_2:"+randomProperties.getSecret2());
    }
}
