package io.bondopangaji.product.application.service;

import io.bondopangaji.feignclient.SupplierClient;
import io.bondopangaji.product.application.port.inbound.command.RegisterProductCommand;
import io.bondopangaji.product.application.port.outbound.PersistProductPort;
import io.bondopangaji.product.application.port.outbound.RabbitMQMessageProducerPort;
import io.bondopangaji.product.application.service.RegisterProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterProductServiceTest {

    @Mock
    private PersistProductPort persistProductPort;

    @Mock
    private SupplierClient supplierClient;

    @Mock
    private RabbitMQMessageProducerPort rabbitMQMessageProducerPort;

    private RegisterProductService registerProductService;

    @BeforeEach
    public void setUp() {
        registerProductService =
                new RegisterProductService(
                        persistProductPort, supplierClient, rabbitMQMessageProducerPort);
    }

    @Test
    @DisplayName("Should throws an exception when the supplier is not exist")
    public void testRegisterWhenSupplierIsNotExistThenThrowsException() {
        RegisterProductCommand command =
                new RegisterProductCommand("name", "description", 100L, 10L, UUID.randomUUID());

        when(supplierClient.checkIfSupplierExist(any(UUID.class))).thenReturn(false);

        assertThrows(RuntimeException.class, () -> registerProductService.register(command));
    }

    @Test
    @DisplayName("Should save the product when the supplier is exist")
    public void testRegisterWhenSupplierIsExistThenSaveProduct() {
        RegisterProductCommand command =
                new RegisterProductCommand("name", "description", 100L, 10L, UUID.randomUUID());

        when(supplierClient.checkIfSupplierExist(any())).thenReturn(true);

        registerProductService.register(command);

        verify(persistProductPort, times(1)).save(any());
    }

    @Test
    @DisplayName("Should publish message to rabbitMQ when the supplier is exist")
    public void testRegisterWhenSupplierIsExistThenPublishMessageToRabbitMQ() {
        RegisterProductCommand command =
                new RegisterProductCommand("name", "description", 100L, 10L, UUID.randomUUID());

        when(supplierClient.checkIfSupplierExist(any(UUID.class))).thenReturn(true);

        registerProductService.register(command);

        verify(rabbitMQMessageProducerPort, times(1)).publish(any(), anyString(), anyString());
    }
}