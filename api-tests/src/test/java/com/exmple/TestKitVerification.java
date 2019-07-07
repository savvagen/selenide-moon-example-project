package com.exmple;

import com.exmple.api_tests.UserServiceTests;
import org.junit.jupiter.api.Test;
import org.junit.platform.testkit.engine.EngineTestKit;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

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



}
