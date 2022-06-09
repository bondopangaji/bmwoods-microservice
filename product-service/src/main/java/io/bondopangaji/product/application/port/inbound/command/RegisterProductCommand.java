/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application.port.inbound.command;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
public record RegisterProductCommand(
        String name,
        String description,
        Long price,
        Long quantity,
        UUID supplierId
) {
}
