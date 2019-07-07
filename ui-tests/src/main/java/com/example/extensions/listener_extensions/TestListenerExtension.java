package com.example.extensions.listener_extensions;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import com.example.extensions.TimingExtension;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import static org.junit.jupiter.api.extension.ExtensionContext.*;

public class TestListenerExtension implements BeforeAllCallback,
        AfterAllCallback,
        BeforeEachCallback,
        AfterEachCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback {


    private static final Logger logger = Logger.getLogger(TestListenerExtension.class.getName());
    private static final String START_TIME = "start time";
    private String value;



    public TestListenerExtension(String value){
        this.value = value;
    }



    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStore(context).put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        long startTime = getStore(context).get(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;
        logger.info(() -> String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
        System.out.println(String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
    }

    private Store getStore(ExtensionContext context) {
        return context.getStore(Namespace.create(getClass(), context.getRequiredTestMethod()));
    }


    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("Executing beforeAll with value:" + value);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("Executing afterAll with value:" + value);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("Executing afterEach with value:" + value);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("Executing beforeEach with value:" + value);
    }


}
