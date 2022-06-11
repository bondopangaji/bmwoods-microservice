/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.application;

import io.bondopangaji.feignclient.SupplierClient;
import io.bondopangaji.product.application.port.inbound.ModifyProductUseCase;
import io.bondopangaji.product.application.port.inbound.command.ModifyProductCommand;
import io.bondopangaji.product.application.port.outbound.GetProductByIdPort;
import io.bondopangaji.product.application.port.outbound.PersistProductPort;
import io.bondopangaji.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Service
public record ModifyProductService(PersistProductPort persistProductPort,
                                   GetProductByIdPort getProductByIdPort,
                                   SupplierClient supplierClient)
        implements ModifyProductUseCase {
    @Override
    public void modify(UUID productId, ModifyProductCommand command) {
        // Fetch check supplier via open feign client
        Boolean checkSupplier = supplierClient.checkIfSupplierExist(command.supplierId());

        // Check if supplier exist
        if (Boolean.FALSE.equals(checkSupplier)) {
            throw new IllegalStateException("Supplier is not Exist");
        }

        // Fetch product from DB
        Product fetchedProduct = getProductByIdPort.getById(productId);

        // Modify and persist fetched product to DB
        fetchedProduct.setName(command.name());
        fetchedProduct.setDescription(command.description());
        fetchedProduct.setPrice(command.price());
        fetchedProduct.setQuantity(command.quantity());
        fetchedProduct.setSupplierId(command.supplierId());
        fetchedProduct.setUpdatedAt(System.currentTimeMillis());

        persistProductPort.save(fetchedProduct);
    }
}
