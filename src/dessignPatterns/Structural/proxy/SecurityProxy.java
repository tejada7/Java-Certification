package dessignPatterns.Structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Favio on 12/11/2017.
 */
public class SecurityProxy implements InvocationHandler {

    private Object obj;

    private SecurityProxy(Object obj) {
        this.obj = obj;
    }

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass()
                .getClassLoader(), obj.getClass().getInterfaces(), new SecurityProxy(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            // Through reflection verify whether the client attempts to invoke a post methodFromInterface.
            if (method.getName().contains("post")) {
                throw new IllegalAccessException("Post operation is not allowed");
            } else {
                result = method.invoke(obj, args);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception " + e.getMessage());
        }
        return result;
    }
}
