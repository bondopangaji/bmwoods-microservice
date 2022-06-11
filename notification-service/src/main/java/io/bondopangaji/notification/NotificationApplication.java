/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Bondo Pangaji
 */

@SpringBootApplication(
        scanBasePackages = {
                "io.bondopangaji.notification",
                "io.bondopangaji.messagequeue"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = {
                "io.bondopangaji.feignclient"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

}
