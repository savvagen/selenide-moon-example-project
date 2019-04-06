package com.example.extensions.parameter_extension;

import com.example.models.Locales;
import com.example.models.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import java.lang.reflect.Parameter;
import java.util.Locale;
import java.util.Random;

public class RandomUserParameterExtension implements ParameterResolver {


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(RandomUser.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return generateUser(
                parameterContext.getParameter(),
                parameterContext.getParameter().getAnnotation(RandomUser.class).locale());
    }


    private Object generateUser(Parameter parameter, Locales.Locale locale){
        Class<?> type = parameter.getType();
        if (User.class.equals(type)){
            Faker faker = new Faker(new Locale(locale.locale));
            return new User().setFirstName(faker.name().firstName())
                    .setLastName(faker.name().lastName())
                    .setUsername(faker.name().username())
                    .setEmail(faker.internet().emailAddress())
                    .setPassword(faker.internet().password())
                    .setId(faker.internet().uuid());
        }
        throw new ParameterResolutionException("No random user is generated for " + type);
    }



}
