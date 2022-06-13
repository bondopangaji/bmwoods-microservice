package io.bondopangaji.notification;
/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Bondo Pangaji
 */
public class ArchitectureTest {
    @Test
    @DisplayName("Should validate hexagonal architecture")
    void validateHexagonalArchitecture() {
        Architectures.onionArchitecture()
                .domainModels("io.bondopangaji.notification.domain..")
                .domainServices("io.bondopangaji.notification.application.service..")
                .applicationServices("io.bondopangaji.notification.application..")
                .adapter("persistence", "io.bondopangaji.notification.adapter.persistence..")
                .adapter("rabbitmq", "io.bondopangaji.notification.adapter.rabbitmq..")
                .adapter("spring", "io.bondopangaji.notification.adapter.spring..")

                .check(new ClassFileImporter()
                        .withImportOption(
                                new com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests())
                        .importPackages("io.bondopangaji.notification"));
    }
}
