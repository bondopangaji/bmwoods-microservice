package io.bondopangaji.supplier.application.service;

import io.bondopangaji.supplier.application.port.outbound.FetchAllSupplierPort;
import io.bondopangaji.supplier.application.service.FetchAllSupplierService;
import io.bondopangaji.supplier.domain.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FetchAllSupplierServiceTest {

    @Mock
    private FetchAllSupplierPort fetchAllSupplierPort;

    @InjectMocks
    private FetchAllSupplierService fetchAllSupplierService;

    @Test
    @DisplayName("Should returns the list of suppliers when the suppliers exist")
    public void testFetchAllWhenSuppliersExistThenReturnsListOfSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(
                Supplier.builder()
                        .id(UUID.randomUUID())
                        .name("Supplier 1")
                        .email("supplier1@gmail.com")
                        .address("Jl. Jendral Sudirman")
                        .zipcode("12345")
                        .city("Jakarta")
                        .country("Indonesia")
                        .createdAt(System.currentTimeMillis())
                        .updatedAt(System.currentTimeMillis())
                        .build());

        suppliers.add(
                Supplier.builder()
                        .id(UUID.randomUUID())
                        .name("Supplier 2")
                        .email("supplier2@gmail.com")
                        .address("Jl. Jendral Sudirman")
                        .zipcode("12345")
                        .city("Jakarta")
                        .country("Indonesia")
                        .createdAt(System.currentTimeMillis())
                        .updatedAt(System.currentTimeMillis())
                        .build());

        when(fetchAllSupplierPort.findAll()).thenReturn(suppliers);

        List<Supplier> result = fetchAllSupplierService.fetchAll();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(fetchAllSupplierPort, times(1)).findAll();
    }

    @Test
    @DisplayName("Should throws an exception when the suppliers do not exist")
    public void testFetchAllWhenSuppliersDoesNotExistThenThrowsException() {
        when(fetchAllSupplierPort.findAll()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> fetchAllSupplierService.fetchAll());
    }
}