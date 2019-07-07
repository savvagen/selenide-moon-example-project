package com.example.extensions.dependency_management;

import com.example.WebService;
import com.example.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class WebServiceParameterResolver implements ParameterResolver {



    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return (parameterContext.getParameter().getType().getSuperclass() == WebService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class<?> type = parameterContext.getParameter().getType();
        Assertions.assertEquals(WebService.class, type.getSuperclass(), "The Object should be a WebService");
        if (WebService.class.equals(type.getSuperclass())) {
            return new UserService("/");
        }
        throw new ParameterResolutionException("No WebService implemented for " + type);
    }



}
