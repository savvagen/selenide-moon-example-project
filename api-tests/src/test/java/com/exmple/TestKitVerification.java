package com.exmple;

import com.exmple.api_tests.UserServiceTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.platform.testkit.engine.EngineTestKit;
import org.opentest4j.TestAbortedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.testkit.engine.EventConditions.*;
import static org.junit.platform.testkit.engine.TestExecutionResultConditions.instanceOf;
import static org.junit.platform.testkit.engine.TestExecutionResultConditions.message;

public class TestKitVerification {



    @Test
    void verifyJupiterContainerStats() {
        EngineTestKit
                .engine("junit-jupiter")
                .selectors(selectClass(UserServiceTests.class))
                .execute()
                .containers()
                .assertStatistics(stats ->
                        stats.started(2).succeeded(2).finished(2).aborted(0).failed(0));
    }

    @Test
    void verifyJupiterTestStats() {
        EngineTestKit
                .engine("junit-jupiter")
                .selectors(selectClass(UserServiceTests.class))
                .execute()
                .tests()
                .assertStatistics(stats ->
                        stats.skipped(1).started(5).succeeded(5).finished(5).aborted(0).failed(0));
    }

//    @Test
//    void verifyJupiterMethodWasSkipped() {
//        String methodName = "skippedTest";
//
//        Events testEvents = EngineTestKit
//                .engine("junit-jupiter")
//                .selectors(selectMethod(ExampleTestCase.class, methodName))
//                .execute()
//                .tests();
//
//        testEvents.assertStatistics(stats -> stats.skipped(1));
//
//        testEvents.assertThatEvents()
//                .haveExactly(1, event(test(methodName),
//                        skippedWithReason("for demonstration purposes")));
//    }
//
//    @Test
//    void verifyJupiterMethodFailed() {
//        EngineTestKit.engine("junit-jupiter")
//                .selectors(selectClass(ExampleTestCase.class))
//                .execute()
//                .tests()
//                .assertThatEvents().haveExactly(1,
//                event(test("failingTest"),
//                        finishedWithFailure(
//                                instanceOf(ArithmeticException.class), message("/ by zero"))));
//    }

    @Test
    void verifyAllJupiterEvents(@TempDir Path tempDir) throws IOException {
        File file = tempDir.resolve("tes_debug.txt").toFile();
        Writer writer = new FileWriter(file); // create a java.io.Writer for debug output
                EngineTestKit.engine("junit-jupiter")
                        .selectors(selectClass(UserServiceTests.class))
                        .execute()
                        .all()
                        .debug(writer)
                        .assertEventsMatchExactly(
                                event(engine(), started()),
                                event(container(UserServiceTests.class), started()),
                                // Examples
                                // event(test("skippedTest"), skippedWithReason("for demonstration purposes")),
                                // event(test("succeedingTest"), started()),
                                // event(test("succeedingTest"), finishedSuccessfully()),
                                // event(test("abortedTest"), started()),
                                // event(test("abortedTest"), abortedWithReason(instanceOf(TestAbortedException.class), message(m -> m.contains("abc does not contain Z")))),
                                // event(test("failingTest"), started()),
                                // event(test("failingTest"), finishedWithFailure(instanceOf(ArithmeticException.class), message("/ by zero"))),
                                // Events assertions
                                event(test("shouldGetSpecificUser"), skippedWithReason("void com.exmple.api_tests.UserServiceTests.shouldGetSpecificUser() is @Disabled")),
                                event(test("shouldGetAllUsers"), started()),
                                event(test("shouldGetAllUsers"), finishedSuccessfully()),
                                event(test("shouldRegisterUser"), started()),
                                event(test("shouldRegisterUser"), finishedSuccessfully()),
                                event(test("shouldDeleteUser"), started()),
                                event(test("shouldDeleteUser"), finishedSuccessfully()),
                                event(test("userShouldLogin"), started()),
                                event(test("userShouldLogin"), finishedSuccessfully()),
                                event(test("userShouldLoginWithCredentials"), started()),
                                event(test("userShouldLoginWithCredentials"), finishedSuccessfully()),
                                event(container(UserServiceTests.class), finishedSuccessfully()),
                                event(engine(), finishedSuccessfully()));
    }


}
