package io.bondopangaji.product;/*
 * Copyright (c) 2022 Bondo Pangaji
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * @author Bondo Pangaji
 */
public class PackageDependencyTest {
    @Test
    @DisplayName("Should validate dependency between package")
    void testPackageDependencies() {
        noClasses()
                .that()
                .resideInAPackage("io.bondopangaji.product.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("io.bondopangaji.product.application..")
                .check(new ClassFileImporter()
                        .importPackages("io.bondopangaji.product.."));
    }
}
