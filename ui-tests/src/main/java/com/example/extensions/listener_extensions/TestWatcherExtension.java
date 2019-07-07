package com.example.extensions.listener_extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestWatcherExtension implements TestWatcher {


    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        System.out.println("Test Disabled.");
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        System.out.println("Test Successful.");
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        System.out.println("Test Aborted.");
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        System.out.println("Test Failed.");
    }
}
