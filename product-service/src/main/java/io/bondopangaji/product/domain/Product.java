/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.domain;

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
public class Product {
    private UUID id;
    private String name;
    private String description;
    private Long price;
    private Long quantity;
    private UUID supplierId;
    private Long createdAt;
    private Long updatedAt;
}