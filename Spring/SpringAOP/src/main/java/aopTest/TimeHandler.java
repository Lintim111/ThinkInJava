package aopTest;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class TimeHandler implements MethodBeforeAdvice, AfterReturningAdvice {


    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("代理----前----CurrentTime = " + System.currentTimeMillis());

    }

    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("代理----后----CurrentTime = " + System.currentTimeMillis());
    }
}
