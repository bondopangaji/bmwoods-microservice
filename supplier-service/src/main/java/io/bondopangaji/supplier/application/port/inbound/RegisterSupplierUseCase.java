/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.application.port.inbound;

import io.bondopangaji.supplier.application.port.inbound.command.RegisterSupplierCommand;

/**
 * @author Bondo Pangaji
 */
public interface RegisterSupplierUseCase {
    void register(RegisterSupplierCommand command);
}
