/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.adapter.persistence;

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
@Entity(name = "supplier_notification")
@AllArgsConstructor
@NoArgsConstructor
public class SupplierNotificationJpaEntity {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Type(type = "pg-uuid")
    private UUID id;
    @Column
    @Type(type = "pg-uuid")
    private UUID toSupplierId;
    @Column
    private String toSupplierEmail;
    @Column
    private String sender;
    @Column
    private String message;
    @Column
    private Long sentAt;
}
