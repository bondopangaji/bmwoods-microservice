/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.application.port.inbound;

import io.bondopangaji.notification.application.port.inbound.command.SendSupplierNotificationCommand;

/**
 * @author Bondo Pangaji
 */
public interface SendSupplierNotificationUseCase {
    void sendAndPersist(SendSupplierNotificationCommand sendSupplierNotificationCommand);
}
