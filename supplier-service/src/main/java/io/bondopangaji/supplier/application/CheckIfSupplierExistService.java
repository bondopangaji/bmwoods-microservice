/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application;

import io.bondopangaji.supplier.application.port.inbound.CheckIfSupplierExistUseCase;
import io.bondopangaji.supplier.application.port.outbound.CheckIfSupplierExistPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Service
public record CheckIfSupplierExistService(CheckIfSupplierExistPort checkIfSupplierExistPort)
        implements CheckIfSupplierExistUseCase {
    @Override
    public boolean checkById(UUID supplierId) {
        return checkIfSupplierExistPort.existById(supplierId);
    }
}
