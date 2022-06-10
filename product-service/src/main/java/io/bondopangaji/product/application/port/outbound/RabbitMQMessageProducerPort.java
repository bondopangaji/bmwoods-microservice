/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application.port.outbound;

/**
 * @author Bondo Pangaji
 */
public interface RabbitMQMessageProducerPort {
    void publish(Object payload, String exchange, String routingKey);
}
