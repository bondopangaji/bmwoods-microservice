/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.adapter.api;

import io.bondopangaji.product.adapter.api.request.ModifyProductRequest;
import io.bondopangaji.product.application.port.inbound.*;
import io.bondopangaji.product.application.port.inbound.command.ModifyProductCommand;
import io.bondopangaji.product.application.port.inbound.command.RegisterProductCommand;
import io.bondopangaji.product.domain.Product;
import io.bondopangaji.product.adapter.api.request.RegisterProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@Slf4j
@RestController
@RequestMapping("api/v1/product")
public record ApiResource(FetchAllProductUseCase fetchAllProductUseCase, RegisterProductUseCase registerProductUseCase,
                          ModifyProductUseCase modifyProductUseCase, RemoveProductUseCase removeProductUseCase,
                          FetchProductUseCase fetchProductUseCase) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> fetchAllProduct() {
        log.info("Fetching all product");
        return fetchAllProductUseCase.fetchAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerProduct(@RequestBody RegisterProductRequest registerProductRequest) {
        log.info("New product registration {}", registerProductRequest.name());
        RegisterProductCommand registerProductCommand = new RegisterProductCommand(
                registerProductRequest.name(),
                registerProductRequest.description(),
                registerProductRequest.price(),
                registerProductRequest.quantity(),
                registerProductRequest.supplierId()
        );
        registerProductUseCase.register(registerProductCommand);
    }

    @PutMapping({"/{productId}"})
    @ResponseStatus(HttpStatus.OK)
    public void modifyProduct(@PathVariable("productId") UUID productId,
                                              @RequestBody ModifyProductRequest modifyProductRequest) {
        ModifyProductCommand command = new ModifyProductCommand(
                modifyProductRequest.name(),
                modifyProductRequest.description(),
                modifyProductRequest.price(),
                modifyProductRequest.quantity(),
                modifyProductRequest.supplierId()
        );
        modifyProductUseCase.modify(productId, command);
    }

    @DeleteMapping({"/{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProduct(@PathVariable("productId") UUID productId) {
        removeProductUseCase.remove(productId);
    }

    @GetMapping({"/{productId}"})
    public ResponseEntity<Product> fetchProduct(@PathVariable("productId") UUID productId) {
        return new ResponseEntity<>(fetchProductUseCase.fetchProductById(productId), HttpStatus.OK);
    }
}
