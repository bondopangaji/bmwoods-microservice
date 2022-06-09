/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application;

import io.bondopangaji.supplier.application.port.inbound.ModifySupplierUseCase;
import io.bondopangaji.supplier.application.port.inbound.command.ModifySupplierCommand;
import io.bondopangaji.supplier.application.port.outbound.GetSupplierByIdPort;
import io.bondopangaji.supplier.application.port.outbound.PersistSupplierPort;
import io.bondopangaji.supplier.domain.Supplier;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Service
public record ModifySupplierService(PersistSupplierPort persistSupplierPort, GetSupplierByIdPort getSupplierByIdPort)
        implements ModifySupplierUseCase {
    @Override
    public void modify(UUID supplierId, ModifySupplierCommand command) {
        // Fetch supplier from DB
        Supplier fetchedSupplier = getSupplierByIdPort.getById(supplierId);

        // Modify and persist fetched supplier to DB
        fetchedSupplier.setName(command.name());
        fetchedSupplier.setEmail(command.email());
        fetchedSupplier.setAddress(command.address());
        fetchedSupplier.setZipcode(command.zipcode());
        fetchedSupplier.setCity(command.city());
        fetchedSupplier.setCountry(command.country());
        fetchedSupplier.setUpdatedAt(System.currentTimeMillis());

        persistSupplierPort.save(fetchedSupplier);
    }
}
