/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.application;

import io.bondopangaji.application.port.inbound.SendSupplierNotificationUseCase;
import io.bondopangaji.application.port.inbound.command.SendSupplierNotificationCommand;
import io.bondopangaji.application.port.outbound.SendSupplierNotificationPort;
import io.bondopangaji.application.port.outbound.WebClientPort;
import io.bondopangaji.domain.SupplierNotification;
import org.springframework.stereotype.Service;

/**
 * @author Bondo Pangaji
 */
@Service
public record SendSupplierNotificationService(SendSupplierNotificationPort sendSupplierNotificationPort,
                                              WebClientPort webClientPort)
        implements SendSupplierNotificationUseCase {
    @Override
    public void sendAndPersist(SendSupplierNotificationCommand sendSupplierNotificationCommand) {

        // Get supplier email
        String email = webClientPort.webClient()
                .get()
                .uri("http://localhost:8080/api/v1/supplier/get-email/", uriBuilder
                        -> uriBuilder.path(String.valueOf(sendSupplierNotificationCommand.toSupplierId())).build())
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Synchronous style

        // Build notification
        SupplierNotification supplierNotification = SupplierNotification.builder()
                .toSupplierId(sendSupplierNotificationCommand.toSupplierId())
                .toSupplierEmail(email)
                .sender("bmwoods")
                .message(
                        "New product registration from bmwoods: " + sendSupplierNotificationCommand.name() +
                                " (" + sendSupplierNotificationCommand.quantity() + ")"
                )
                .sentAt(System.currentTimeMillis())
                .build();

        // TODO: Send notification with Spring Email via Google Mail SMTP

        // Persist notification data to DB
        sendSupplierNotificationPort.save(supplierNotification);
    }
}
