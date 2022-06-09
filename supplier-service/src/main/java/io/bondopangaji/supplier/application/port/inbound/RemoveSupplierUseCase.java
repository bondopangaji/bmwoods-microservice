/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application.port.inbound;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
public interface RemoveSupplierUseCase {
    void remove(UUID productId);
}
