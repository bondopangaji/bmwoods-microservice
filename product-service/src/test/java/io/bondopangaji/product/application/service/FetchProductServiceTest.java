package io.bondopangaji.product.application.service;

import io.bondopangaji.product.application.service.FetchProductService;
import io.bondopangaji.product.application.port.outbound.GetProductByIdPort;
import io.bondopangaji.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FetchProductServiceTest {

    @Mock
    private GetProductByIdPort getProductByIdPort;

    @InjectMocks
    private FetchProductService fetchProductService;

    @Test
    @DisplayName("Should returns the product when the product exists")
    public void testFetchProductByIdWhenProductExistsThenReturnsTheProduct() {
        UUID productId = UUID.randomUUID();
        Product product =
                Product.builder()
                        .id(productId)
                        .name("Product 1")
                        .description("Product 1 description")
                        .price(100L)
                        .quantity(10L)
                        .supplierId(UUID.randomUUID())
                        .createdAt(System.currentTimeMillis())
                        .updatedAt(System.currentTimeMillis())
                        .build();

        when(getProductByIdPort.getById(productId)).thenReturn(product);

        Product actualProduct = fetchProductService.fetchProductById(productId);

        assertEquals(product, actualProduct);
    }

    @Test
    @DisplayName("Should throws an exception when the product does not exist")
    public void testFetchProductByIdWhenProductDoesNotExistThenThrowsException() {
        UUID productId = UUID.randomUUID();
        when(getProductByIdPort.getById(productId)).thenReturn(null);

        assertThrows(
                RuntimeException.class,
                () -> fetchProductService.fetchProductById(productId));

        verify(getProductByIdPort, times(1)).getById(productId);
    }
}