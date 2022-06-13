/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application.service;

import io.bondopangaji.product.application.port.inbound.RemoveProductUseCase;
import io.bondopangaji.product.application.port.outbound.RemoveProductPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Service
public record RemoveProductService(RemoveProductPort removeProductPort) implements RemoveProductUseCase {
    @Override
    public void remove(UUID productId) {
        removeProductPort.deleteById(productId);
    }
}
