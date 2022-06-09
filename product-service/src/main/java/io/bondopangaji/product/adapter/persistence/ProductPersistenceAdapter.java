/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.adapter.persistence;

import io.bondopangaji.product.application.port.outbound.FetchAllProductPort;
import io.bondopangaji.product.application.port.outbound.GetProductByIdPort;
import io.bondopangaji.product.application.port.outbound.PersistProductPort;
import io.bondopangaji.product.application.port.outbound.RemoveProductPort;
import io.bondopangaji.product.domain.Product;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Bondo Pangaji
 */
@Component
public record ProductPersistenceAdapter(ProductRepository productRepository, ProductMapper productMapper)
        implements FetchAllProductPort, PersistProductPort, GetProductByIdPort, RemoveProductPort {

    @Override
    public List<Product> findAll() {
        return productMapper.toProductList(productRepository.findAll());
    }

    @Override
    public void save(Product product) {
        productRepository.save(productMapper.productToProductJpa(product));
    }

    @Override
    public Product getById(UUID productId) {
        return productMapper.productJpaToProduct(productRepository.getById(productId));
    }

    @Override
    public void deleteById(UUID productId) {
        productRepository.deleteById(productId);
    }
}
