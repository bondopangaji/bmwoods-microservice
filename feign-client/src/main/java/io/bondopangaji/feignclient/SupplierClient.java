/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

/**
 * @author Bondo Pangaji
 */
@FeignClient(
        value = "supplier-service",
        path = "api/v1/supplier"
)
public interface SupplierClient {
    @GetMapping("/check/{supplier-id}")
    @ResponseStatus(HttpStatus.OK)
    boolean checkIfSupplierExist(@PathVariable("supplier-id") UUID supplierId);

    @GetMapping({"/get-email/{supplierId}"})
    @ResponseStatus(HttpStatus.OK)
    String getSupplierEmail(@PathVariable("supplierId") UUID supplierId);
}
