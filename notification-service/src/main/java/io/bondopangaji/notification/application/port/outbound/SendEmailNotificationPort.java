/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.application.port.outbound;

import io.bondopangaji.notification.domain.SupplierNotification;

/**
 * @author Bondo Pangaji
 */
public interface SendEmailNotificationPort {
    void sendMessages(SupplierNotification supplierNotification);
}
