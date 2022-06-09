/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.adapter.api.request;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
public record SendSupplierNotificationRequest(
        UUID toSupplierId,
        String name,
        Long quantity
) {
}
