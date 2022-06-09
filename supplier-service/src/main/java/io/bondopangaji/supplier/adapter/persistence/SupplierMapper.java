/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.adapter.persistence;

import io.bondopangaji.supplier.domain.Supplier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bondo Pangaji
 */
@Component
public class SupplierMapper {
    public List<Supplier> toSupplierList(List<SupplierJpaEntity> supplierJpaEntity) {
        if (supplierJpaEntity == null) {
            return null;
        }
        List<Supplier> list = new ArrayList<>(supplierJpaEntity.size());
        for (SupplierJpaEntity supplierJpa : supplierJpaEntity) {
            list.add( supplierJpaToSupplier(supplierJpa));
        }
        return list;
    }

    public Supplier supplierJpaToSupplier(SupplierJpaEntity supplierJpa) {
        return new Supplier(
                supplierJpa.getId() == null ? null : supplierJpa.getId(),
                supplierJpa.getName(),
                supplierJpa.getEmail(),
                supplierJpa.getAddress(),
                supplierJpa.getZipcode(),
                supplierJpa.getCity(),
                supplierJpa.getCountry(),
                supplierJpa.getCreatedAt(),
                supplierJpa.getUpdatedAt()
        );
    }

    public SupplierJpaEntity supplierToSupplierJpa(Supplier supplier) {
        return new SupplierJpaEntity(
                supplier.getId() == null ? null : supplier.getId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getZipcode(),
                supplier.getCity(),
                supplier.getCountry(),
                supplier.getCreatedAt(),
                supplier.getUpdatedAt()
        );
    }
}
