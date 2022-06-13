/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.supplier.adapter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bondopangaji.supplier.adapter.api.ApiResource;
import io.bondopangaji.supplier.adapter.api.request.ModifySupplierRequest;
import io.bondopangaji.supplier.adapter.api.request.RegisterSupplierRequest;
import io.bondopangaji.supplier.application.port.inbound.*;
import io.bondopangaji.supplier.application.port.inbound.command.ModifySupplierCommand;
import io.bondopangaji.supplier.application.port.inbound.command.RegisterSupplierCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bondo Pangaji
 */
@WebMvcTest(controllers = ApiResource.class)
public class MvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FetchAllSupplierUseCase fetchAllSupplierUseCase;

    @MockBean
    private RegisterSupplierUseCase registerSupplierUseCase;

    @MockBean
    private ModifySupplierUseCase modifySupplierUseCase;

    @MockBean
    private RemoveSupplierUseCase removeSupplierUseCase;

    @MockBean
    private FetchSupplierUseCase fetchSupplierUseCase;

    @MockBean
    private CheckIfSupplierExistUseCase checkIfSupplierExistUseCase;

    @MockBean
    private FetchSupplierEmailUseCase fetchSupplierEmailUseCase;

    @Test
    @DisplayName("Should display status 200 and trigger fetchAll()")
    public void testFetchAllSupplier() throws Exception {
        mockMvc.perform(
                get("/api/v1/supplier"))
                .andExpect(status().isOk());

        then(fetchAllSupplierUseCase).should()
                .fetchAll();
    }

    @Test
    @DisplayName("Should display status 201 and trigger register()")
    public void testRegisterSupplier() throws Exception {
        RegisterSupplierRequest request = new RegisterSupplierRequest(
                "name",
                "email",
                "address",
                "zipcode",
                "city",
                "country");

        RegisterSupplierCommand command = new RegisterSupplierCommand(
                "name",
                "email",
                "address",
                "zipcode",
                "city",
                "country");

        mockMvc.perform(
                post("/api/v1/supplier")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        then(registerSupplierUseCase).should()
                .register(command);
    }

    @Test
    @DisplayName("Should display status 200 and trigger modify()")
    public void testModifySupplier() throws Exception {
        ModifySupplierRequest request = new ModifySupplierRequest(
                "name",
                "email",
                "address",
                "zipcode",
                "city",
                "country");

        ModifySupplierCommand command = new ModifySupplierCommand(
                "name",
                "email",
                "address",
                "zipcode",
                "city",
                "country");

        UUID supplierId = UUID.randomUUID();

        mockMvc.perform(
                put("/api/v1/supplier/{supplierId}", supplierId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        then(modifySupplierUseCase).should()
                .modify(supplierId, command);
    }

    @Test
    @DisplayName("Should display status 200 and trigger fetchSupplierById()()")
    public void testFetchSupplier() throws Exception {
        UUID supplierId = UUID.randomUUID();

        mockMvc.perform(
                get("/api/v1/supplier/{supplierId}", supplierId))
                .andExpect(status().isOk());

        then(fetchSupplierUseCase).should()
                .fetchSupplierById(supplierId);
    }

    @Test
    @DisplayName("Should display status 204 and trigger remove()")
    public void testRemoveSupplier() throws Exception {
        UUID supplierId = UUID.randomUUID();

        mockMvc.perform(
                delete("/api/v1/supplier/{supplierId}", supplierId))
                .andExpect(status().isNoContent());

        then(removeSupplierUseCase).should()
                .remove(supplierId);
    }

    @Test
    @DisplayName("Should display status 200 and trigger checkById()")
    public void testCheckIfSupplierExist() throws Exception {
        UUID supplierId = UUID.randomUUID();

        mockMvc.perform(
                get("/api/v1/supplier/check/{supplierId}", supplierId))
                .andExpect(status().isOk());

        then(checkIfSupplierExistUseCase).should()
                .checkById(supplierId);
    }

    @Test
    @DisplayName("Should display status 200 and trigger fetchSupplierById()")
    public void testFetchSupplierEmail() throws Exception {
        UUID supplierId = UUID.randomUUID();

        mockMvc.perform(
                        get("/api/v1/supplier/get-email/{supplierId}", supplierId))
                .andExpect(status().isOk());

        then(fetchSupplierEmailUseCase).should()
                .fetchSupplierById(supplierId);
    }
}
