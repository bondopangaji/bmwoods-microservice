/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application.port.inbound;

import io.bondopangaji.product.application.port.inbound.command.RegisterProductCommand;

/**
 * @author Bondo Pangaji
 */
public interface RegisterProductUseCase {
    void register(RegisterProductCommand registerProductCommand);
}
