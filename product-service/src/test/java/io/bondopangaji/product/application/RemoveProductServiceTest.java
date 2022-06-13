package io.bondopangaji.product.application;

import io.bondopangaji.product.application.port.outbound.RemoveProductPort;
import io.bondopangaji.product.application.service.RemoveProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RemoveProductServiceTest {

    @Mock
    private RemoveProductPort removeProductPort;

    private RemoveProductService removeProductService;

    @Test
    @DisplayName("Should delete the product when the product exists")
    public void testRemoveWhenProductExists() {
        UUID productId = UUID.randomUUID();
        removeProductService = new RemoveProductService(removeProductPort);
        removeProductService.remove(productId);
        verify(removeProductPort).deleteById(productId);
    }

    @Test
    @DisplayName("Should throws an exception when the product does not exist")
    public void testRemoveWhenProductDoesNotExistThenThrowsException() {
        UUID productId = UUID.randomUUID();
        removeProductService = new RemoveProductService(removeProductPort);
        doThrow(RuntimeException.class).when(removeProductPort).deleteById(productId);

        assertThrows(RuntimeException.class, () -> removeProductService.remove(productId));

        verify(removeProductPort, times(1)).deleteById(productId);
    }
}