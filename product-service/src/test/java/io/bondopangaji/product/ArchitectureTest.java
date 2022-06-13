package io.bondopangaji.product;/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

/**
 * @author Bondo Pangaji
 */
public class ArchitectureTest {
    @Test
    void validateHexagonalArchitecture() {
        Architectures.onionArchitecture()
                .domainModels("io.bondopangaji.product.domain..")
                .domainServices("io.bondopangaji.product.application")
                .applicationServices("io.bondopangaji.product.application.service")
                .adapter("api", "io.bondopangaji.product.adapter.api")
                .adapter("persistence", "io.bondopangaji.product.adapter.persistence")
                .adapter("rabbitmq", "io.bondopangaji.product.adapter.rabbitmq")
                .adapter("spring", "io.bondopangaji.product.adapter.spring")
                .check(new ClassFileImporter()
                        .importPackages("io.bondopangaji.product"));
    }
}
