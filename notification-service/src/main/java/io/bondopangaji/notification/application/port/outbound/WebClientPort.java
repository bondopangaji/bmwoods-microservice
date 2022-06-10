/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

package io.bondopangaji.notification.application.port.outbound;

import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Bondo Pangaji
 */
public interface WebClientPort {
    WebClient webClient();
}
