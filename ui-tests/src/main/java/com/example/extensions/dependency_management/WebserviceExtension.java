package com.example.extensions.dependency_management;

import com.codeborne.selenide.Selenide;
import com.example.WebService;
import com.example.pages.WebPage;
import com.example.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;

public class WebserviceExtension implements ParameterResolver {


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface Microservice {
    }


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(Microservice.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return getMicroservice(parameterContext.getParameter());
    }



    private Object getMicroservice(Parameter parameter){
        Class<?> type = parameter.getType();
        Assertions.assertEquals(WebService.class, type.getSuperclass(), "The Object should be a WebService");
        if (WebService.class.equals(type.getSuperclass())) {
            return new UserService("/");
        }
        throw new ParameterResolutionException("No WebService implemented for " + type);
    }

}
