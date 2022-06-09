/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application.port.outbound;

import io.bondopangaji.product.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Bondo Pangaji
 */
@Component
public interface FetchAllProductPort {
    List<Product> findAll();
}
