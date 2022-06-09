/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.application.port.outbound;

import io.bondopangaji.domain.SupplierNotification;
import org.springframework.stereotype.Component;

/**
 * @author Bondo Pangaji
 */
public interface SendSupplierNotificationPort {
    void save(SupplierNotification supplierNotification);
}
