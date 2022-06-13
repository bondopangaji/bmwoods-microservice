/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application.service;

import io.bondopangaji.supplier.application.port.inbound.FetchSupplierUseCase;
import io.bondopangaji.supplier.application.port.outbound.GetSupplierByIdPort;
import io.bondopangaji.supplier.domain.Supplier;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Service
public record FetchSupplierService(GetSupplierByIdPort getSupplierByIdPort) implements FetchSupplierUseCase {
    @Override
    public Supplier fetchSupplierById(UUID supplierId) {
        return getSupplierByIdPort.getById(supplierId);
    }
}
