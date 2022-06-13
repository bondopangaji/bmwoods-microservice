/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application.service;

import io.bondopangaji.product.application.port.inbound.FetchAllProductUseCase;
import io.bondopangaji.product.application.port.outbound.FetchAllProductPort;
import io.bondopangaji.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bondo Pangaji
 */
@Service
public record FetchAllProductService(FetchAllProductPort fetchAllProductPort) implements FetchAllProductUseCase {
    @Override
    public List<Product> fetchAll() {
        return fetchAllProductPort.findAll();
    }
}

