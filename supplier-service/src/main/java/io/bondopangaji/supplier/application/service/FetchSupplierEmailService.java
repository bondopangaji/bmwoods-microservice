/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application.service;

import io.bondopangaji.supplier.application.port.inbound.FetchSupplierEmailUseCase;
import io.bondopangaji.supplier.application.port.outbound.FetchSupplierEmailPort;
import io.bondopangaji.supplier.domain.Supplier;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Service
public record FetchSupplierEmailService(FetchSupplierEmailPort fetchSupplierEmailPort) implements FetchSupplierEmailUseCase {
    @Override
    public String fetchSupplierById(UUID supplierId) {
        // Fetch supplier email from DB
        String fetchedSupplierEmail = fetchSupplierEmailPort.getSupplierEmailById(supplierId);

        // Check if supplier email does not exist
        if (fetchedSupplierEmail == null) {
            throw new RuntimeException("Supplier email is not exist!");
        }

        return fetchedSupplierEmail;
    }
}
