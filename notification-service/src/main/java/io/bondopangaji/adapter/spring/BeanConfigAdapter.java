/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.adapter.spring;

import io.bondopangaji.application.port.outbound.WebClientPort;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Bondo Pangaji
 */
@Configuration
public class BeanConfigAdapter implements WebClientPort {
    @Bean
    @LoadBalanced
    @Override
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
