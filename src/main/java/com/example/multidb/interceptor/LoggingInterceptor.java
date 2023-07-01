package com.example.multidb.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingInterceptor {

    @Around("@annotation(com.example.multidb.annotation.LoggingAnnotation)")
    public Object loggingRequest(ProceedingJoinPoint jp) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("before invoke method :"+objectMapper.writeValueAsString(jp.getArgs()));
        Object resp = jp.proceed();
        System.out.println("invoke function seccess");
        return resp;
    }
}
