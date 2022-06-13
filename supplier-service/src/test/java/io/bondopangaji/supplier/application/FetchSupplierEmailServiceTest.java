package io.bondopangaji.supplier.application;

import io.bondopangaji.supplier.application.port.outbound.FetchSupplierEmailPort;
import io.bondopangaji.supplier.application.service.FetchSupplierEmailService;
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
public class FetchSupplierEmailServiceTest {

    @Mock
    private FetchSupplierEmailPort fetchSupplierEmailPort;

    @InjectMocks
    private FetchSupplierEmailService fetchSupplierEmailService;

    @Test
    @DisplayName("Should returns the supplier email when the supplier email is exist")
    public void testFetchSupplierByIdWhenSupplierEmailIsExistThenReturnsTheSupplierEmail() {
        UUID supplierId = UUID.randomUUID();
        String supplierEmail = "supplier@email.com";
        when(fetchSupplierEmailPort.getSupplierEmailById(supplierId)).thenReturn(supplierEmail);

        String fetchedSupplierEmail = fetchSupplierEmailService.fetchSupplierById(supplierId);

        assertEquals(supplierEmail, fetchedSupplierEmail);
    }

    @Test
    @DisplayName("Should throws an exception when the supplier email is not exist")
    public void testFetchSupplierByIdWhenSupplierEmailIsNotExistThenThrowsException() {
        UUID supplierId = UUID.randomUUID();
        when(fetchSupplierEmailPort.getSupplierEmailById(supplierId)).thenReturn(null);

        assertThrows(
                RuntimeException.class,
                () -> fetchSupplierEmailService.fetchSupplierById(supplierId));

        verify(fetchSupplierEmailPort, times(1)).getSupplierEmailById(supplierId);
    }
}