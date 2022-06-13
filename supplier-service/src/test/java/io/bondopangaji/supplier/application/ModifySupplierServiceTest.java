package io.bondopangaji.supplier.application;

import io.bondopangaji.supplier.application.port.inbound.command.ModifySupplierCommand;
import io.bondopangaji.supplier.application.port.outbound.GetSupplierByIdPort;
import io.bondopangaji.supplier.application.port.outbound.PersistSupplierPort;
import io.bondopangaji.supplier.application.service.ModifySupplierService;
import io.bondopangaji.supplier.domain.Supplier;
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
public class ModifySupplierServiceTest {

    @Mock
    private PersistSupplierPort persistSupplierPort;

    @Mock
    private GetSupplierByIdPort getSupplierByIdPort;

    private ModifySupplierService modifySupplierService;

    @BeforeEach
    public void setUp() {
        modifySupplierService = new ModifySupplierService(persistSupplierPort, getSupplierByIdPort);
    }

    @Test
    @DisplayName("Should throws an exception when the supplier is not exist")
    public void testModifyWhenSupplierIsNotExistThenThrowsException() {
        UUID supplierId = UUID.randomUUID();
        ModifySupplierCommand command =
                new ModifySupplierCommand("name", "email", "address", "zipcode", "city", "country");

        when(getSupplierByIdPort.getById(supplierId)).thenReturn(null);

        assertThrows(
                RuntimeException.class, () -> modifySupplierService.modify(supplierId, command));
    }

    @Test
    @DisplayName("Should update the supplier when the supplier is exist")
    public void testModifyWhenSupplierIsExistThenUpdateTheSupplier() {
        UUID supplierId = UUID.randomUUID();
        ModifySupplierCommand command =
                new ModifySupplierCommand("name", "email", "address", "zipcode", "city", "country");
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

        modifySupplierService.modify(supplierId, command);

        verify(persistSupplierPort, times(1)).save(supplier);
    }
}