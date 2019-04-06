package com.exmple;

import com.exmple.tests.CatalogServiceTests;
import com.exmple.tests.UserServiceTests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

@Tag("suite")
@Execution(ExecutionMode.CONCURRENT)
@RunWith(JUnitPlatform.class)
@SelectClasses({UserServiceTests.class, CatalogServiceTests.class})
@IncludeTags({"users", "catalog"})
@ExcludeTags("fast")
@SuiteDisplayName("My Test Suite")
public class Suite {
}


