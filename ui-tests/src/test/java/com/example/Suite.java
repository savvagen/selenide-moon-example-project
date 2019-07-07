package com.example;

import com.example.ui_tests.LoginTests;
import com.example.ui_tests.RegistrationTests;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;


//@Execution(ExecutionMode.CONCURRENT)
@RunWith(JUnitPlatform.class)
@SelectClasses({LoginTests.class, RegistrationTests.class})
class Suite{}