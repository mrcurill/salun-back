package ru.sbrf.muza_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Before("@annotation(ru.sbrf.muza_service.aspect.LogRequestResponse)")
    public void myAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        StringBuilder argsStrBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            argsStrBuilder.append(args[i]);
            if (i < args.length - 1) {
                argsStrBuilder.append(",");
            }
        }

        log.info("REQUEST ===> {}, args=[{}]",
                getClassMethod(joinPoint),
                argsStrBuilder.toString());
    }

    @Around("@annotation(ru.sbrf.muza_service.aspect.LogRequestResponse)")
    public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        Object value;

        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            log.info(
                    " {} {} from {}",
                    request.getMethod(),
                    request.getRequestURI(),
                    request.getRemoteAddr());

        }

        return value;
    }

    @AfterReturning(value = "@annotation(ru.sbrf.muza_service.aspect.LogRequestResponse)", returning = "result")
    public void myAdvice2(JoinPoint joinPoint, Object result) {
        log.info("RESPONSE ===> {}, {}",
                getClassMethod(joinPoint),
                result);
    }

    private String getClassMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getDeclaringClass().getCanonicalName() + "." + method.getName();
    }
}