package io.bondopangaji.supplier.application.service;

import io.bondopangaji.supplier.application.port.outbound.GetSupplierByIdPort;
import io.bondopangaji.supplier.application.service.FetchSupplierService;
import io.bondopangaji.supplier.domain.Supplier;
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
public class FetchSupplierServiceTest {

    @Mock
    private GetSupplierByIdPort getSupplierByIdPort;

    @InjectMocks
    private FetchSupplierService fetchSupplierService;

    @Test
    @DisplayName("Should return the supplier when the supplier is exist")
    public void testFetchSupplierByIdWhenSupplierIsExistThenReturnTheSupplier() {
        UUID supplierId = UUID.randomUUID();
        Supplier supplier =
                Supplier.builder()
                        .id(supplierId)
                        .name("name")
                        .email("email")
                        .address("address")
                        .zipcode("zipcode")
                        .city("city")
                        .country("country")
                        .createdAt(System.currentTimeMillis())
                        .updatedAt(System.currentTimeMillis())
                        .build();

        when(getSupplierByIdPort.getById(supplierId)).thenReturn(supplier);

        Supplier fetchedSupplier = fetchSupplierService.fetchSupplierById(supplierId);

        assertEquals(supplier, fetchedSupplier);
    }

    @Test
    @DisplayName("Should throws an exception when the supplier is not exist")
    public void testFetchSupplierByIdWhenSupplierIsNotExistThenThrowsException() {
        UUID supplierId = UUID.randomUUID();
        when(getSupplierByIdPort.getById(supplierId)).thenReturn(null);
        assertThrows(
                RuntimeException.class, () -> fetchSupplierService.fetchSupplierById(supplierId));
        verify(getSupplierByIdPort, times(1)).getById(supplierId);
    }
}