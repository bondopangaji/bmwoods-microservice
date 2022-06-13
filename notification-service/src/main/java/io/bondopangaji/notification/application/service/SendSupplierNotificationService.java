/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.application.service;

import io.bondopangaji.feignclient.SupplierClient;
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
                                              SupplierClient supplierClient,
                                              SendEmailNotificationPort sendEmailNotificationPort)
        implements SendSupplierNotificationUseCase {
    @Override
    public void sendAndPersist(SendSupplierNotificationCommand sendSupplierNotificationCommand) {
        // Fetch supplier email via open feign client
        String email = supplierClient.getSupplierEmail(sendSupplierNotificationCommand.toSupplierId());

        // Check if supplier email does not exist
        if (email == null) {
            throw new RuntimeException("Supplier email does not exist");
        }

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
