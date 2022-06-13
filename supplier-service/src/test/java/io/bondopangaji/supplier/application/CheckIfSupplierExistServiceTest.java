package io.bondopangaji.supplier.application;

import io.bondopangaji.supplier.application.port.outbound.CheckIfSupplierExistPort;
import io.bondopangaji.supplier.application.service.CheckIfSupplierExistService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckIfSupplierExistServiceTest {

    @Mock
    private CheckIfSupplierExistPort checkIfSupplierExistPort;

    @InjectMocks
    private CheckIfSupplierExistService checkIfSupplierExistService;

    @Test
    @DisplayName("Should return true when the supplier exists")
    public void testCheckByIdWhenSupplierExistsThenReturnTrue() {
        UUID supplierId = UUID.randomUUID();
        when(checkIfSupplierExistPort.existById(supplierId)).thenReturn(true);

        boolean result = checkIfSupplierExistService.checkById(supplierId);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should throw an exception when the supplier does not exist")
    public void testCheckByIdWhenSupplierDoesNotExistThenThrowException() {
        UUID supplierId = UUID.randomUUID();
        when(checkIfSupplierExistPort.existById(supplierId)).thenReturn(false);

        assertThrows(
                RuntimeException.class, () -> checkIfSupplierExistService.checkById(supplierId));

        verify(checkIfSupplierExistPort, times(1)).existById(supplierId);
    }
}