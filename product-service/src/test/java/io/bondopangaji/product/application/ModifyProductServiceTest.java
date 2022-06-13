package io.bondopangaji.product.application;

import io.bondopangaji.feignclient.SupplierClient;
import io.bondopangaji.product.application.port.inbound.command.ModifyProductCommand;
import io.bondopangaji.product.application.port.outbound.GetProductByIdPort;
import io.bondopangaji.product.application.port.outbound.PersistProductPort;
import io.bondopangaji.product.application.service.ModifyProductService;
import io.bondopangaji.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModifyProductServiceTest {

    @Mock
    private PersistProductPort persistProductPort;

    @Mock
    private GetProductByIdPort getProductByIdPort;

    @Mock
    private SupplierClient supplierClient;

    private ModifyProductService modifyProductService;

    @BeforeEach
    public void setUp() {
        modifyProductService =
                new ModifyProductService(persistProductPort, getProductByIdPort, supplierClient);
    }

    @Test
    @DisplayName("Should throws an exception when the supplier is not exist")
    public void testModifyWhenSupplierIsNotExistThenThrowsException() {
        UUID productId = UUID.randomUUID();
        ModifyProductCommand command =
                new ModifyProductCommand("name", "description", 100L, 10L, UUID.randomUUID());
        when(supplierClient.checkIfSupplierExist(command.supplierId())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> modifyProductService.modify(productId, command));
    }

    @Test
    @DisplayName("Should throws an exception when the product is not exist")
    public void testModifyWhenProductIsNotExistThenThrowsException() {
        UUID productId = UUID.randomUUID();
        ModifyProductCommand command =
                new ModifyProductCommand("name", "description", 100L, 10L, UUID.randomUUID());

        assertThrows(RuntimeException.class, () -> modifyProductService.modify(productId, command));
    }

    @Test
    @DisplayName("Should update the product when the supplier and product are exist")
    public void testModifyWhenSupplierAndProductAreExistThenUpdateTheProduct() {
        UUID productId = UUID.randomUUID();
        ModifyProductCommand command =
                new ModifyProductCommand("name", "description", 100L, 10L, UUID.randomUUID());
        Product product =
                Product.builder()
                        .id(productId)
                        .name("name")
                        .description("description")
                        .price(100L)
                        .quantity(10L)
                        .supplierId(command.supplierId())
                        .createdAt(System.currentTimeMillis())
                        .updatedAt(System.currentTimeMillis())
                        .build();

        when(supplierClient.checkIfSupplierExist(command.supplierId())).thenReturn(true);
        when(getProductByIdPort.getById(productId)).thenReturn(product);

        modifyProductService.modify(productId, command);

        verify(supplierClient, times(1)).checkIfSupplierExist(command.supplierId());
        verify(getProductByIdPort, times(1)).getById(productId);
        verify(persistProductPort, times(1)).save(product);
    }
}