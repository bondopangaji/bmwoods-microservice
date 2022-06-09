/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.adapter.api;

import io.bondopangaji.supplier.adapter.api.request.ModifySupplierRequest;
import io.bondopangaji.supplier.adapter.api.request.SupplierRegistrationRequest;
import io.bondopangaji.supplier.application.port.inbound.*;
import io.bondopangaji.supplier.application.port.inbound.command.ModifySupplierCommand;
import io.bondopangaji.supplier.application.port.inbound.command.RegisterSupplierCommand;
import io.bondopangaji.supplier.domain.Supplier;
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
public record ApiResource(RegisterSupplierUseCase registerSupplierUseCase,
                          FetchAllSupplierUseCase fetchAllSupplierUseCase,
                          CheckIfSupplierExistUseCase checkIfSupplierExistUseCase,
                          ModifySupplierUseCase modifySupplierUseCase, RemoveSupplierUseCase removeSupplierUseCase,
                          FetchSupplierUseCase fetchSupplierUseCase,
                          FetchSupplierEmailUseCase fetchSupplierEmailUseCase) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Supplier> fetchAllSupplier() {
        log.info("Fetching all supplier");
        return fetchAllSupplierUseCase.fetchAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerSupplier(@RequestBody SupplierRegistrationRequest supplierRegistrationRequest) {
        log.info("New supplier registration {}", supplierRegistrationRequest.name());
        RegisterSupplierCommand command = new RegisterSupplierCommand(
                supplierRegistrationRequest.name(),
                supplierRegistrationRequest.email(),
                supplierRegistrationRequest.address(),
                supplierRegistrationRequest.zipcode(),
                supplierRegistrationRequest.city(),
                supplierRegistrationRequest.country()
        );
        registerSupplierUseCase.register(command);
    }

    @GetMapping("/check/{supplier-id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkIfSupplierExist(@PathVariable("supplier-id") UUID supplierId) {
        log.info("Check if supplier exist by id: {}", supplierId);
        return checkIfSupplierExistUseCase.checkById(supplierId);
    }

    @PutMapping({"/{supplierId}"})
    @ResponseStatus(HttpStatus.OK)
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
    public void removeSupplier(@PathVariable("supplierId") UUID supplierId) {
        removeSupplierUseCase.remove(supplierId);
    }

    @GetMapping({"/{supplierId}"})
    public ResponseEntity<Supplier> fetchSupplier(@PathVariable("supplierId") UUID supplierId) {
        return new ResponseEntity<>(fetchSupplierUseCase.fetchSupplierById(supplierId), HttpStatus.OK);
    }

    @GetMapping({"/get-email/{supplierId}"})
    public String getSupplierEmail(@PathVariable("supplierId") UUID supplierId) {
        return fetchSupplierEmailUseCase.fetchSupplierById(supplierId);
    }
}
