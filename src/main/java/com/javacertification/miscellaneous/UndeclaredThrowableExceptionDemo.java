package com.javacertification.miscellaneous;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static java.lang.reflect.Proxy.newProxyInstance;

public class UndeclaredThrowableExceptionDemo {

    static class MyCheckedException extends Exception {
    }

    interface Service {
        void doSomething() throws MyCheckedException;
    }

    public static void main(String[] args) {
        Service service = (Service) Proxy.newInstance(new ServiceStub());
        try {
            service.doSomething();
        } catch (Exception e) {
            assert e instanceof MyCheckedException; // fails and we instead get an UndeclaredThrowableException, why?
        }
    }

    static class ServiceStub implements Service {
        @Override
        public void doSomething() throws MyCheckedException {
            throw new MyCheckedException();
        }
    }

    static class Proxy implements InvocationHandler {

        private final Object obj;

        Proxy(Object obj) {
            this.obj = obj;
        }

        public static Object newInstance(Object obj) {
            return newProxyInstance(
                obj.getClass()
                    .getClassLoader(),
                obj.getClass().getInterfaces(),
                new Proxy(obj)
            );
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(obj, args);
        }
    }
}
