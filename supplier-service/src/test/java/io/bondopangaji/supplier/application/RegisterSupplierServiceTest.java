package io.bondopangaji.supplier.application;

import io.bondopangaji.supplier.application.port.inbound.command.ModifySupplierCommand;
import io.bondopangaji.supplier.application.port.inbound.command.RegisterSupplierCommand;
import io.bondopangaji.supplier.application.port.outbound.PersistSupplierPort;
import io.bondopangaji.supplier.application.service.RegisterSupplierService;
import io.bondopangaji.supplier.domain.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterSupplierServiceTest {

    @Mock
    private PersistSupplierPort persistSupplierPort;

    private RegisterSupplierService registerSupplierService;

    @BeforeEach
    public void setUp() {
        registerSupplierService = new RegisterSupplierService(persistSupplierPort);
    }

    @Test
    @DisplayName("Should saves the supplier")
    public void testRegisterSupplier() {
        RegisterSupplierCommand command =
                new RegisterSupplierCommand("name", "email", "address", "zipcode", "city", "country");

        registerSupplierService.register(command);

        verify(persistSupplierPort, times(1)).save(any());
    }

}