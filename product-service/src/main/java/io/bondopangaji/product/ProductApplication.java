/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Bondo Pangaji
 */
@SpringBootApplication(
        scanBasePackages = {
                "io.bondopangaji.product",
                "io.bondopangaji.messagequeue"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "io.bondopangaji.feignclient"
)
@EnableWebMvc
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
