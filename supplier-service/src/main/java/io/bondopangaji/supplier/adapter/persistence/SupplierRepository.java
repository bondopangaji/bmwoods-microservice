/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Repository
public interface SupplierRepository extends JpaRepository<SupplierJpaEntity, UUID> {
    @Query(
            value = "select case when count (s)> 0 then true else false end from supplier s where s.id = ?1"
    )
    boolean existByUUID(UUID supplierId);

    @Query(
            value = "select s.email from supplier s where s.id = ?1"
    )
    String getSupplierEmailById(UUID id);
}
