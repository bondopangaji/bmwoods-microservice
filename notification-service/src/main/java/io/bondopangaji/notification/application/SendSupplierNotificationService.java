/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.application;

import io.bondopangaji.notification.application.port.inbound.SendSupplierNotificationUseCase;
import io.bondopangaji.notification.application.port.inbound.command.SendSupplierNotificationCommand;
import io.bondopangaji.notification.application.port.outbound.*;
import io.bondopangaji.notification.domain.SupplierNotification;
import org.springframework.stereotype.Service;

/**
 * @author Bondo Pangaji
 */
@Service
public record SendSupplierNotificationService(SendSupplierNotificationPort sendSupplierNotificationPort,
                                              WebClientPort webClientPort,
                                              SendEmailNotificationPort sendEmailNotificationPort)
        implements SendSupplierNotificationUseCase {
    @Override
    public void sendAndPersist(SendSupplierNotificationCommand sendSupplierNotificationCommand) {

        // Internal comms
        String email = webClientPort.webClient()
                .get()
                .uri("http://localhost:8082/api/v1/supplier/get-email/", uriBuilder
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

        // Send notification with Spring Email via Google Mail SMTP
        sendEmailNotificationPort.sendMessages(supplierNotification);

        // Persist notification data to DB
        sendSupplierNotificationPort.save(supplierNotification);
    }
}
