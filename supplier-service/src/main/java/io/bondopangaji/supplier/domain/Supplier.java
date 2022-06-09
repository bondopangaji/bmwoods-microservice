/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.domain;

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
public class Supplier {
    private UUID id;
    private String name;
    private String email;
    private String address;
    private String zipcode;
    private String city;
    private String country;
    private Long createdAt;
    private Long updatedAt;
}
