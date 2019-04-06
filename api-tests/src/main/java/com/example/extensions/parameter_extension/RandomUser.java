package com.example.extensions.parameter_extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static com.example.models.Locales.Locale;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RandomUser {
    Locale locale() default Locale.UNITED_STATES;
}


