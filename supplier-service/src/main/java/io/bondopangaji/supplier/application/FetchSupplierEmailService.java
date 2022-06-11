/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application;

import io.bondopangaji.supplier.application.port.inbound.FetchSupplierEmailUseCase;
import io.bondopangaji.supplier.application.port.outbound.FetchSupplierEmailPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Service
public record FetchSupplierEmailService(FetchSupplierEmailPort fetchSupplierEmailPort) implements FetchSupplierEmailUseCase {
    @Override
    public String fetchSupplierById(UUID supplierId) {
        return fetchSupplierEmailPort.getSupplierEmailById(supplierId);
    }
}
