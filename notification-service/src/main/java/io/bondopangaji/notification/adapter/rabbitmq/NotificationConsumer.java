/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.adapter.rabbitmq;

import io.bondopangaji.notification.adapter.rabbitmq.request.SendSupplierNotificationRequest;
import io.bondopangaji.notification.application.port.inbound.SendSupplierNotificationUseCase;
import io.bondopangaji.notification.application.port.inbound.command.SendSupplierNotificationCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Bondo Pangaji
 */
@Component
public record NotificationConsumer(SendSupplierNotificationUseCase sendSupplierNotificationUseCase) {
    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumeSendNotification(SendSupplierNotificationRequest sendSupplierNotificationRequest) {
        SendSupplierNotificationCommand command = new SendSupplierNotificationCommand(
                sendSupplierNotificationRequest.toSupplierId(),
                sendSupplierNotificationRequest.name(),
                sendSupplierNotificationRequest.quantity()
        );
        sendSupplierNotificationUseCase.sendAndPersist(command);
    }
}
