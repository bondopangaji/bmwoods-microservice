package io.bondopangaji.notification.adapter.rabbitmq;

import io.bondopangaji.notification.adapter.rabbitmq.request.SendSupplierNotificationRequest;
import io.bondopangaji.notification.application.port.inbound.SendSupplierNotificationUseCase;
import io.bondopangaji.notification.application.port.inbound.command.SendSupplierNotificationCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NotificationConsumerTest {

    @Mock
    private SendSupplierNotificationUseCase sendSupplierNotificationUseCase;

    private NotificationConsumer notificationConsumer;

    @BeforeEach
    public void setUp() {
        notificationConsumer = new NotificationConsumer(sendSupplierNotificationUseCase);
    }

    @Test
    @DisplayName("Should send and persist the notification when the request is valid")
    public void testConsumeSendNotificationWhenRequestIsValidThenSendAndPersistTheNotification() {
        UUID toSupplierId = UUID.randomUUID();
        String name = "name";
        Long quantity = 1L;
        SendSupplierNotificationRequest sendSupplierNotificationRequest =
                new SendSupplierNotificationRequest(toSupplierId, name, quantity);
        SendSupplierNotificationCommand sendSupplierNotificationCommand =
                new SendSupplierNotificationCommand(toSupplierId, name, quantity);

        notificationConsumer.consumeSendNotification(sendSupplierNotificationRequest);

        verify(sendSupplierNotificationUseCase, times(1))
                .sendAndPersist(sendSupplierNotificationCommand);
    }
}