/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.adapter.api.request;

/**
 * @author Bondo Pangaji
 */
public record RegisterSupplierRequest(
        String name,
        String email,
        String address,
        String zipcode,
        String city,
        String country
) {
}
