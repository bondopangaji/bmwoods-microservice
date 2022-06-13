/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Bondo Pangaji
 */
@SpringBootApplication
@EnableEurekaClient
@EnableWebMvc
public class SupplierApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplierApplication.class, args);
    }
}
