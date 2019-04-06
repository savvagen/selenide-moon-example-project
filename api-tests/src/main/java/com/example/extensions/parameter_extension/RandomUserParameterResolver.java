package com.example.extensions.parameter_extension;

import com.example.models.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Locale;

public class RandomUserParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return (parameterContext.getParameter().getType() == User.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Faker faker = new Faker(new Locale("en-US"));
        return new User()
                        .setFirstName(faker.name().firstName())
                        .setLastName(faker.name().lastName())
                        .setUsername(faker.name().username())
                        .setEmail(faker.internet().emailAddress())
                        .setPassword(faker.internet().password())
                        .setId(faker.internet().uuid());
    }

}
