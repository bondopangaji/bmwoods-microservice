/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.adapter.api;

import io.bondopangaji.notification.application.port.inbound.SendSupplierNotificationUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bondo Pangaji
 */
@Slf4j
@RestController
@RequestMapping("api/v1/notification")
public record ApiResource(SendSupplierNotificationUseCase sendSupplierNotificationUseCase) {
    // Intentionally emptied
    // Rabbit MQ async comms implemented
}
