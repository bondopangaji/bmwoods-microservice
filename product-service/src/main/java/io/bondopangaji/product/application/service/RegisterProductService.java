/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application.service;

import io.bondopangaji.feignclient.SupplierClient;
import io.bondopangaji.product.application.port.inbound.RegisterProductUseCase;
import io.bondopangaji.product.application.port.inbound.command.RegisterProductCommand;
import io.bondopangaji.product.application.port.inbound.command.SendSupplierNotificationCommand;
import io.bondopangaji.product.application.port.outbound.PersistProductPort;
import io.bondopangaji.product.application.port.outbound.RabbitMQMessageProducerPort;
import io.bondopangaji.product.domain.Product;
import org.springframework.stereotype.Service;


/**
 * @author Bondo Pangaji
 */
@Service
public record RegisterProductService(PersistProductPort persistProductPort, SupplierClient supplierClient,
                                     RabbitMQMessageProducerPort rabbitMQMessageProducerPort)
        implements RegisterProductUseCase {
    @Override
    public void register(RegisterProductCommand command) {
        // Fetch check supplier via open feign client
        Boolean checkSupplier = supplierClient.checkIfSupplierExist(command.supplierId());

        // Check if supplier does not exist
        if (Boolean.FALSE.equals(checkSupplier)) {
            throw new RuntimeException("Supplier does not Exist");
        }

        // Create and persist product to DB
        Product product = Product.builder()
                .name(command.name())
                .description(command.description())
                .price(command.price())
                .quantity(command.quantity())
                .supplierId(command.supplierId())
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .build();

        persistProductPort.save(product);

        // Notify supplier that product has been registered
        SendSupplierNotificationCommand sendSupplierNotificationCommand = new SendSupplierNotificationCommand(
                command.supplierId(),
                command.name(),
                command.quantity()
        );

        rabbitMQMessageProducerPort.publish(
                sendSupplierNotificationCommand,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
