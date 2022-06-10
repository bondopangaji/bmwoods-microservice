/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.adapter.spring;

import io.bondopangaji.notification.application.port.outbound.SendEmailNotificationPort;
import io.bondopangaji.notification.domain.SupplierNotification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Bondo Pangaji
 */
@Component
public record EmailServiceAdapter(JavaMailSender javaMailSender) implements SendEmailNotificationPort {
    @Override
    public void sendMessages(SupplierNotification supplierNotification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(supplierNotification.getSender());
        message.setTo(supplierNotification.getToSupplierEmail());
        message.setSubject("Notification");
        message.setText(supplierNotification.getMessage());
        javaMailSender.send(message);
    }
}
