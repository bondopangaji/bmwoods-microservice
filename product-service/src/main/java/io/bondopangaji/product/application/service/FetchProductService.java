/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application.service;

import io.bondopangaji.product.application.port.inbound.FetchProductUseCase;
import io.bondopangaji.product.application.port.outbound.GetProductByIdPort;
import io.bondopangaji.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Service
public record FetchProductService(GetProductByIdPort getProductByIdPort) implements FetchProductUseCase {
    @Override
    public Product fetchProductById(UUID productId) {
        // Fetch product from DB
        Product fetchedProduct = getProductByIdPort.getById(productId);

        // Check if product is not exist
        if (fetchedProduct == null) {
            throw new RuntimeException("Product is not exist!");
        }

        return fetchedProduct;
    }
}
