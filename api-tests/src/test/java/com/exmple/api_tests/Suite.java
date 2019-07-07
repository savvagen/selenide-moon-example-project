package com.exmple.api_tests;

import com.exmple.api_tests.CatalogServiceTests;
import com.exmple.api_tests.UserServiceTests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@Tag("suite")
@RunWith(JUnitPlatform.class)
@SelectClasses({UserServiceTests.class, CatalogServiceTests.class})
public class Suite {
}


