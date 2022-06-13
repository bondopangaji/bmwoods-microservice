package io.bondopangaji.notification.application.service;

import io.bondopangaji.feignclient.SupplierClient;
import io.bondopangaji.notification.application.port.inbound.command.SendSupplierNotificationCommand;
import io.bondopangaji.notification.application.port.outbound.SendEmailNotificationPort;
import io.bondopangaji.notification.application.port.outbound.SendSupplierNotificationPort;
import io.bondopangaji.notification.application.service.SendSupplierNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SendSupplierNotificationServiceTest {

    @Mock
    private SendSupplierNotificationPort sendSupplierNotificationPort;
    @Mock
    private SupplierClient supplierClient;
    @Mock
    private SendEmailNotificationPort sendEmailNotificationPort;

    private SendSupplierNotificationService sendSupplierNotificationService;

    @BeforeEach
    public void setUp() {
        sendSupplierNotificationService =
                new SendSupplierNotificationService(
                        sendSupplierNotificationPort, supplierClient, sendEmailNotificationPort);
    }

    @Test
    @DisplayName("Should throws an exception when the supplier email does not exist")
    public void testSendAndPersistWhenSupplierEmailDoesNotExistThenThrowsException() {
        UUID supplierId = UUID.randomUUID();
        String name = "name";
        Long quantity = 1L;
        SendSupplierNotificationCommand sendSupplierNotificationCommand =
                new SendSupplierNotificationCommand(supplierId, name, quantity);
        when(supplierClient.getSupplierEmail(supplierId)).thenReturn(null);

        Throwable exception =
                assertThrows(
                        RuntimeException.class,
                        () ->
                                sendSupplierNotificationService.sendAndPersist(
                                        sendSupplierNotificationCommand));

        assertEquals("Supplier email does not exist", exception.getMessage());
    }

    @Test
    @DisplayName("Should saves the notification when the supplier email exists")
    public void testSendAndPersistWhenSupplierEmailExistsThenSavesTheNotification() {
        UUID supplierId = UUID.randomUUID();
        String supplierEmail = "test@test.com";
        SendSupplierNotificationCommand sendSupplierNotificationCommand =
                new SendSupplierNotificationCommand(supplierId, "test", 1L);
        when(supplierClient.getSupplierEmail(supplierId)).thenReturn(supplierEmail);

        sendSupplierNotificationService.sendAndPersist(sendSupplierNotificationCommand);

        verify(sendEmailNotificationPort, times(1)).sendMessages(any());
        verify(sendSupplierNotificationPort, times(1)).save(any());
    }
}