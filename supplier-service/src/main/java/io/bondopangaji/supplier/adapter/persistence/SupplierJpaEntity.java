/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.adapter.persistence;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Getter
@Setter
@Builder
@Entity(name = "supplier")
@AllArgsConstructor
@NoArgsConstructor
public class SupplierJpaEntity {
        @Id
        @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
        @GeneratedValue(generator = "uuid-gen")
        @Type(type = "pg-uuid")
        private UUID id;
        @Column(nullable = false)
        private String name;
        @Column(nullable = false)
        private String email;
        @Column(nullable = false)
        private String address;
        @Column(nullable = false)
        private String zipcode;
        @Column(nullable = false)
        private String city;
        @Column(nullable = false)
        private String country;
        @Column(name = "created_at")
        private Long createdAt;
        @Column(name = "updated_at")
        private Long updatedAt;
}
