/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.adapter.rabbitmq;

import io.bondopangaji.messagequeue.RabbitMQMessageProducer;
import io.bondopangaji.product.application.port.outbound.RabbitMQMessageProducerPort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bondo Pangaji
 */
@Configuration
@AllArgsConstructor
public class RabbitMQAdapter implements RabbitMQMessageProducerPort {
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @Override
    public void publish(Object payload, String exchange, String routingKey) {
        rabbitMQMessageProducer.publish(payload, exchange, routingKey);
    }
}