/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.product.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bondopangaji.product.adapter.api.ApiResource;
import io.bondopangaji.product.adapter.api.request.ModifyProductRequest;
import io.bondopangaji.product.adapter.api.request.RegisterProductRequest;
import io.bondopangaji.product.application.port.inbound.*;
import io.bondopangaji.product.application.port.inbound.command.ModifyProductCommand;
import io.bondopangaji.product.application.port.inbound.command.RegisterProductCommand;
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
    private FetchAllProductUseCase fetchAllProductUseCase;

    @MockBean
    private RegisterProductUseCase registerProductUseCase;

    @MockBean
    private ModifyProductUseCase modifyProductUseCase;

    @MockBean
    private RemoveProductUseCase removeProductUseCase;

    @MockBean
    private FetchProductUseCase fetchProductUseCase;

    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("Should display status 200 and trigger fetchAll()")
    public void testFetchAllProduct() throws Exception {
        mockMvc.perform(
                get("/api/v1/product"))
                .andExpect(status().isOk());

        then(fetchAllProductUseCase).should()
                .fetchAll();
    }

    @Test
    @DisplayName("Should display status 201 and trigger register()")
    public void testRegisterProduct() throws Exception {
        RegisterProductRequest request
                = new RegisterProductRequest("name", "description", 100L, 10L, UUID.randomUUID());

        RegisterProductCommand command = new RegisterProductCommand(
                request.name(),
                request.description(),
                request.price(),
                request.quantity(),
                request.supplierId()
        );

        mockMvc.perform(
                post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        then(registerProductUseCase).should()
                .register(command);
    }

    @Test
    @DisplayName("Should display status 200 and trigger modify()")
    public void testModifyProduct() throws Exception {
        ModifyProductRequest request
                = new ModifyProductRequest("name", "description", 100L, 10L, UUID.randomUUID());

        ModifyProductCommand command = new ModifyProductCommand(
                request.name(),
                request.description(),
                request.price(),
                request.quantity(),
                request.supplierId()
        );

        UUID productId = UUID.randomUUID();

        mockMvc.perform(
                put("/api/v1/product/{productId}", productId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        then(modifyProductUseCase).should()
                .modify(productId, command);
    }

    @Test
    @DisplayName("Should display status 200 and trigger fetchProductById()")
    public void testFetchProduct() throws Exception {
        UUID productId = UUID.randomUUID();

        mockMvc.perform(
                get("/api/v1/product/{productId}", productId))
                .andExpect(status().isOk());

        then(fetchProductUseCase).should()
                .fetchProductById(productId);
    }

    @Test
    @DisplayName("Should display status 204 and trigger remove()")
    public void removeProduct() throws Exception {
        UUID productId = UUID.randomUUID();

        mockMvc.perform(
                delete("/api/v1/product/{productId}", productId))
                .andExpect(status().isNoContent());

        then(removeProductUseCase).should()
                .remove(productId);
    }
}
