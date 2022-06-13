package io.bondopangaji.supplier.application.service;

import io.bondopangaji.supplier.application.port.outbound.RemoveSupplierPort;
import io.bondopangaji.supplier.application.service.RemoveSupplierService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RemoveSupplierServiceTest {

    @Mock
    private RemoveSupplierPort removeSupplierPort;

    @InjectMocks
    private RemoveSupplierService removeSupplierService;

    @Test
    @DisplayName("Should delete the supplier when the supplier exists")
    public void testRemoveWhenSupplierExists() {
        UUID supplierId = UUID.randomUUID();
        removeSupplierService.remove(supplierId);
        verify(removeSupplierPort).deleteById(supplierId);
    }
}