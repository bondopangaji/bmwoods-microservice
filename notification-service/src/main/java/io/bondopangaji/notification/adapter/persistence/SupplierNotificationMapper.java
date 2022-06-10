/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.adapter.persistence;

import io.bondopangaji.notification.domain.SupplierNotification;
import org.springframework.stereotype.Component;

/**
 * @author Bondo Pangaji
 */
@Component
public class SupplierNotificationMapper {
    public SupplierNotification supplierNotificationJpaToSupplierNotification
            (SupplierNotificationJpaEntity supplierNotificationJpa) {
        return new SupplierNotification(
                supplierNotificationJpa.getId() == null ? null : supplierNotificationJpa.getId(),
                supplierNotificationJpa.getToSupplierId(),
                supplierNotificationJpa.getToSupplierEmail(),
                supplierNotificationJpa.getSender(),
                supplierNotificationJpa.getMessage(),
                supplierNotificationJpa.getSentAt()
        );
    }

    public SupplierNotificationJpaEntity supplierNotificationToSupplierNotificationJpa
            (SupplierNotification supplierNotification) {
        return new SupplierNotificationJpaEntity(
                supplierNotification.getId() == null ? null : supplierNotification.getId(),
                supplierNotification.getToSupplierId(),
                supplierNotification.getToSupplierEmail(),
                supplierNotification.getSender(),
                supplierNotification.getMessage(),
                supplierNotification.getSentAt()
        );
    }
}
