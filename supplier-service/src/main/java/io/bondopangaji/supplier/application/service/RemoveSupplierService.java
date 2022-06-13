/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application.service;

import io.bondopangaji.supplier.application.port.inbound.RemoveSupplierUseCase;
import io.bondopangaji.supplier.application.port.outbound.RemoveSupplierPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Service
public record RemoveSupplierService(RemoveSupplierPort removeSupplierPort) implements RemoveSupplierUseCase {
    @Override
    public void remove(UUID productId) {
        removeSupplierPort.deleteById(productId);
    }
}
