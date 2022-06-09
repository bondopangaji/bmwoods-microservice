/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application;

import io.bondopangaji.supplier.application.port.inbound.FetchAllSupplierUseCase;
import io.bondopangaji.supplier.application.port.outbound.FetchAllSupplierPort;
import io.bondopangaji.supplier.domain.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bondo Pangaji
 */
@Service
public record FetchAllSupplierService(FetchAllSupplierPort fetchAllSupplierPort) implements FetchAllSupplierUseCase {
    @Override
    public List<Supplier> fetchAll() {
        return fetchAllSupplierPort.findAll();
    }
}
