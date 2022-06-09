/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.adapter.persistence;

import io.bondopangaji.application.port.outbound.SendSupplierNotificationPort;
import io.bondopangaji.domain.SupplierNotification;
import org.springframework.stereotype.Component;

/**
 * @author Bondo Pangaji
 */
@Component
public record SupplierNotificationPersistenceAdapter(SupplierNotificationRepository supplierNotificationRepository,
                                                     SupplierNotificationMapper supplierNotificationMapper)
        implements SendSupplierNotificationPort {
    @Override
    public void save(SupplierNotification supplierNotification) {
        supplierNotificationRepository.save(supplierNotificationMapper.supplierNotificationToSupplierNotificationJpa(supplierNotification));
    }
}
