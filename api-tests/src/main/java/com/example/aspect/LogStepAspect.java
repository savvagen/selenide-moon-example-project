package com.example.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Slf4j
public class LogStepAspect {

    @Pointcut("execution(* com.example.services.*.*(..))")  // -> (2)
    public void anyMethod(){
        //pointcut body, should be empty
    }


    @Before("anyMethod()")
    public void step(JoinPoint joinPoint) throws Throwable {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String methodName = joinPoint.getArgs().length > 0
                ? String.format("%s (%s)", methodSignature.getName(), arrayToString(joinPoint.getArgs())) // -> (1)
                : methodSignature.getName() + "()";

        log.info(methodName);
    }





    private static String arrayToString(final Object... array) {
        return Stream.of(array)
                .map(object -> {
                    if (object.getClass().isArray()) {
                        return arrayToString((Object[]) object);
                    }
                    return Objects.toString(object);
                })
                .collect(Collectors.joining(", "));
        //return Stream.of(array).map(Objects::toString).collect(Collectors.joining(", "));
    }

}
