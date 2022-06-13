package io.bondopangaji.supplier;
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
                .domainModels("io.bondopangaji.supplier.domain..")
                .domainServices("io.bondopangaji.supplier.application.service..")
                .applicationServices("io.bondopangaji.supplier.application..")
                .adapter("api", "io.bondopangaji.supplier.adapter.api..")
                .adapter("persistence", "io.bondopangaji.supplier.adapter.persistence..")
                .check(new ClassFileImporter()
                        .withImportOption(
                                new com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests())
                        .importPackages("io.bondopangaji.supplier"));
    }
}
