package com.hsd.gitlab;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;


@EnableEurekaClient
//@EnableConfigServer
@SpringCloudApplication
@Slf4j
public class GitlabHooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitlabHooksApplication.class, args);
        log.info("Application is success!");
    }
}
