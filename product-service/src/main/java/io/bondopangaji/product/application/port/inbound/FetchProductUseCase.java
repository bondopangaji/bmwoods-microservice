/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application.port.inbound;

import io.bondopangaji.product.domain.Product;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
public interface FetchProductUseCase {
    Product fetchProductById(UUID productId);
}
