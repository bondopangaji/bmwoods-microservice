package io.bondopangaji.product.application.service;

import io.bondopangaji.product.application.port.outbound.FetchAllProductPort;
import io.bondopangaji.product.application.service.FetchAllProductService;
import io.bondopangaji.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FetchAllProductServiceTest {

    @Mock
    private FetchAllProductPort fetchAllProductPort;

    @InjectMocks
    private FetchAllProductService fetchAllProductService;

    @Test
    @DisplayName("Should returns all products")
    public void testFetchAllReturnsAllProducts() {
        List<Product> products =
                List.of(
                        Product.builder()
                                .id(UUID.randomUUID())
                                .name("Product 1")
                                .description("Description 1")
                                .price(100L)
                                .quantity(10L)
                                .supplierId(UUID.randomUUID())
                                .createdAt(System.currentTimeMillis())
                                .updatedAt(System.currentTimeMillis())
                                .build(),
                        Product.builder()
                                .id(UUID.randomUUID())
                                .name("Product 2")
                                .description("Description 2")
                                .price(200L)
                                .quantity(20L)
                                .supplierId(UUID.randomUUID())
                                .createdAt(System.currentTimeMillis())
                                .updatedAt(System.currentTimeMillis())
                                .build());

        when(fetchAllProductPort.findAll()).thenReturn(products);

        List<Product> actualProducts = fetchAllProductService.fetchAll();

        assertEquals(products, actualProducts);

        verify(fetchAllProductPort, times(1)).findAll();

        verifyNoMoreInteractions(fetchAllProductPort);
    }

    @Test
    @DisplayName("Should throws an exception when the products do not exist")
    public void testFetchAllWhenProductsDoNotExistThenThrowsException() {
        when(fetchAllProductPort.findAll()).thenReturn(null);

        assertThrows(RuntimeException.class, () -> fetchAllProductService.fetchAll());
    }

}