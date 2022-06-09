/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.adapter.persistence;

import io.bondopangaji.supplier.application.port.outbound.*;
import io.bondopangaji.supplier.domain.Supplier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Component
public record SupplierPersistenceAdapter(SupplierRepository supplierRepository, SupplierMapper supplierMapper)
        implements FetchAllSupplierPort, PersistSupplierPort, CheckIfSupplierExistPort, GetSupplierByIdPort,
        RemoveSupplierPort, FetchSupplierEmailPort {
    @Override
    public List<Supplier> findAll() {
        return supplierMapper.toSupplierList(supplierRepository.findAll());
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplierMapper.supplierToSupplierJpa(supplier));
    }

    @Override
    public boolean existById(UUID supplierId) {
        return supplierRepository.existByUUID(supplierId);
    }

    @Override
    public Supplier getById(UUID supplierId) {
        return supplierMapper.supplierJpaToSupplier(supplierRepository.getById(supplierId));
    }

    @Override
    public void deleteById(UUID supplierId) {
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public String getSupplierEmailById(UUID id) {
        return supplierRepository.getSupplierEmailById(id);
    }
}
