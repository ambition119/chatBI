package com.am.chat.bi;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = { "com.am.chat.bi.dao" })
public class ChatBIApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ChatBIApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ChatBIApplication.class, args);
        LOG.info("ChatBIApplication start run!");
    }
}
