/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.domain;

import lombok.*;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierNotification {
    private UUID id;
    private UUID toSupplierId;
    private String toSupplierEmail;
    private String sender;
    private String message;
    private Long sentAt;
}
