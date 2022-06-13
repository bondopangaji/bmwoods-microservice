package io.bondopangaji.product;/*
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
                .domainModels("io.bondopangaji.product.domain..")
                .domainServices("io.bondopangaji.product.application.service..")
                .applicationServices("io.bondopangaji.product.application..")
                .adapter("api", "io.bondopangaji.product.adapter.api..")
                .adapter("persistence", "io.bondopangaji.product.adapter.persistence..")
                .adapter("rabbitmq", "io.bondopangaji.product.adapter.rabbitmq..")
                .check(new ClassFileImporter()
                        .withImportOption(
                                new com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests())
                        .importPackages("io.bondopangaji.product"));
    }
}
