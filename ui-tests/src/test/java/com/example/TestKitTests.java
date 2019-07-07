package com.example;

import com.example.ui_tests.TestExtensionsExample;
import org.junit.jupiter.api.Test;
import org.junit.platform.testkit.engine.EngineTestKit;
import org.junit.platform.testkit.engine.Events;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;


public class TestKitTests {


    @Test
    void verifyJupiterContainerStats() {
        EngineTestKit
                .engine("junit-jupiter")
                .selectors(selectClass(TestExtensionsExample.class))
                .execute()
                .containers()
                .assertStatistics(stats -> stats.started(2).succeeded(2).aborted(0).failed(0));
    }

    @Test
    void verifyJupiterTestStats() {
        EngineTestKit
                .engine("junit-jupiter")
                .selectors(selectClass(TestExtensionsExample.class))
                .execute()
                .tests()
                .assertStatistics(stats ->
                        stats.skipped(0).started(3).succeeded(3).aborted(0).failed(0));
    }



}
