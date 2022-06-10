/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application;

import io.bondopangaji.product.application.port.inbound.RegisterProductUseCase;
import io.bondopangaji.product.application.port.inbound.command.RegisterProductCommand;
import io.bondopangaji.product.application.port.inbound.command.SendSupplierNotificationCommand;
import io.bondopangaji.product.application.port.outbound.PersistProductPort;
import io.bondopangaji.product.application.port.outbound.RabbitMQMessageProducerPort;
import io.bondopangaji.product.application.port.outbound.WebClientPort;
import io.bondopangaji.product.domain.Product;
import org.springframework.stereotype.Service;

/**
 * @author Bondo Pangaji
 */
@Service
public record RegisterProductService(PersistProductPort persistProductPort, WebClientPort webClientPort,
                                     RabbitMQMessageProducerPort rabbitMQMessageProducerPort)
        implements RegisterProductUseCase {
    @Override
    public void register(RegisterProductCommand registerProductCommand) {
        // Fetch check supplier via synchronous webclient
        Boolean checkSupplier = webClientPort.webClient()
                .get()
                .uri("http://localhost:8082/api/v1/supplier/check/", uriBuilder
                        -> uriBuilder.path(String.valueOf(registerProductCommand.supplierId())).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block(); // Synchronous style

        // Check if supplier exist
        if (Boolean.FALSE.equals(checkSupplier)) {
            throw new IllegalStateException("Supplier is not Exist");
        }

        // Create and persist product to DB
        Product product = Product.builder()
                .name(registerProductCommand.name())
                .description(registerProductCommand.description())
                .price(registerProductCommand.price())
                .quantity(registerProductCommand.quantity())
                .supplierId(registerProductCommand.supplierId())
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .build();

        persistProductPort.save(product);

        // Notify supplier that product has been registered
        SendSupplierNotificationCommand sendSupplierNotificationCommand = new SendSupplierNotificationCommand(
                registerProductCommand.supplierId(),
                registerProductCommand.name(),
                registerProductCommand.quantity()
        );

        rabbitMQMessageProducerPort.publish(
                sendSupplierNotificationCommand,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
