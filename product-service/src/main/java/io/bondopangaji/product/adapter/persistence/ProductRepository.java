/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductJpaEntity, UUID>{
    @Query(
            value = "SELECT CASE WHEN COUNT (p)> 0 THEN TRUE ELSE FALSE END FROM product p WHERE p.id = ?1"
    )
    boolean existByUUID(UUID productId);
}
