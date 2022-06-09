/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application.port.outbound;

import io.bondopangaji.supplier.domain.Supplier;

/**
 * @author Bondo Pangaji
 */
public interface PersistSupplierPort {
    void save(Supplier supplier);
}
