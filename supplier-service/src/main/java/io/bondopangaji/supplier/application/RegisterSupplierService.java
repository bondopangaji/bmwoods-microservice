/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application;

import io.bondopangaji.supplier.application.port.inbound.RegisterSupplierUseCase;
import io.bondopangaji.supplier.application.port.inbound.command.RegisterSupplierCommand;
import io.bondopangaji.supplier.application.port.outbound.PersistSupplierPort;
import io.bondopangaji.supplier.domain.Supplier;
import org.springframework.stereotype.Service;

@Service
public record RegisterSupplierService(PersistSupplierPort persistSupplierPort) implements RegisterSupplierUseCase {
    @Override
    public void register(RegisterSupplierCommand command) {
        // Create and persist supplier to DB
        Supplier supplier = Supplier.builder()
                .name(command.name())
                .email(command.email())
                .address(command.address())
                .zipcode(command.zipcode())
                .city(command.city())
                .country(command.country())
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .build();

        persistSupplierPort.save(supplier);
    }
}
