/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.adapter.api;

import io.bondopangaji.supplier.BeanConfig;
import io.bondopangaji.supplier.adapter.api.request.ModifySupplierRequest;
import io.bondopangaji.supplier.adapter.api.request.RegisterSupplierRequest;
import io.bondopangaji.supplier.application.port.inbound.*;
import io.bondopangaji.supplier.application.port.inbound.command.ModifySupplierCommand;
import io.bondopangaji.supplier.application.port.inbound.command.RegisterSupplierCommand;
import io.bondopangaji.supplier.domain.Supplier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("api/v1/supplier")
@Api(tags = {BeanConfig.SUPPLIER_TAG})
public record ApiResource(RegisterSupplierUseCase registerSupplierUseCase,
                          FetchAllSupplierUseCase fetchAllSupplierUseCase,
                          CheckIfSupplierExistUseCase checkIfSupplierExistUseCase,
                          ModifySupplierUseCase modifySupplierUseCase, RemoveSupplierUseCase removeSupplierUseCase,
                          FetchSupplierUseCase fetchSupplierUseCase,
                          FetchSupplierEmailUseCase fetchSupplierEmailUseCase) {
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of supplier")
    public List<Supplier> fetchAllSupplier() {
        log.info("Fetching all supplier");
        return fetchAllSupplierUseCase.fetchAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Register a new supplier")
    public void registerSupplier(@RequestBody RegisterSupplierRequest registerSupplierRequest) {
        log.info("New supplier registration {}", registerSupplierRequest.name());
        RegisterSupplierCommand command = new RegisterSupplierCommand(
                registerSupplierRequest.name(),
                registerSupplierRequest.email(),
                registerSupplierRequest.address(),
                registerSupplierRequest.zipcode(),
                registerSupplierRequest.city(),
                registerSupplierRequest.country()
        );
        registerSupplierUseCase.register(command);
    }

    @GetMapping("/check/{supplierId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Check if supplier exist")
    public boolean checkIfSupplierExist(@PathVariable("supplierId") UUID supplierId) {
        log.info("Check if supplier exist by id: {}", supplierId);
        return checkIfSupplierExistUseCase.checkById(supplierId);
    }

    @PutMapping(value = "/{supplierId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Modify a supplier")
    public void modifySupplier(@PathVariable("supplierId") UUID supplierId,
                              @RequestBody ModifySupplierRequest modifySupplierRequest) {
        ModifySupplierCommand command = new ModifySupplierCommand(
                modifySupplierRequest.name(),
                modifySupplierRequest.email(),
                modifySupplierRequest.address(),
                modifySupplierRequest.zipcode(),
                modifySupplierRequest.city(),
                modifySupplierRequest.country()
        );
        modifySupplierUseCase.modify(supplierId, command);
    }

    @DeleteMapping({"/{supplierId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a supplier")
    public void removeSupplier(@PathVariable("supplierId") UUID supplierId) {
        removeSupplierUseCase.remove(supplierId);
    }

    @GetMapping({"/{supplierId}"})
    @ApiOperation(value = "Fetch a supplier")
    public ResponseEntity<Supplier> fetchSupplier(@PathVariable("supplierId") UUID supplierId) {
        return new ResponseEntity<>(fetchSupplierUseCase.fetchSupplierById(supplierId), HttpStatus.OK);
    }

    @GetMapping( value = "/get-email/{supplierId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get supplier email")
    public String getSupplierEmail(@PathVariable("supplierId") UUID supplierId) {
        return fetchSupplierEmailUseCase.fetchSupplierById(supplierId);
    }
}
