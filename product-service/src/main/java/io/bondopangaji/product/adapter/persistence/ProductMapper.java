/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.adapter.persistence;

import io.bondopangaji.product.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bondo Pangaji
 */
@Component
public class ProductMapper {

    public List<Product> toProductList(List<ProductJpaEntity> productJpaEntity) {
        if (productJpaEntity == null) {
            return null;
        }
        List<Product> list = new ArrayList<>(productJpaEntity.size());
        for (ProductJpaEntity productJpa : productJpaEntity) {
            list.add(productJpaToProduct(productJpa));
        }
        return list;
    }

    public Product productJpaToProduct(ProductJpaEntity productJpa) {
            return new Product(
                    productJpa.getId() == null ? null : productJpa.getId(),
                    productJpa.getName(),
                    productJpa.getDescription(),
                    productJpa.getPrice(),
                    productJpa.getQuantity(),
                    productJpa.getSupplierId(),
                    productJpa.getCreatedAt(),
                    productJpa.getUpdatedAt()
            );
    }

    public ProductJpaEntity productToProductJpa(Product product) {
        return new ProductJpaEntity (
                product.getId() == null ? null : product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getSupplierId(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
