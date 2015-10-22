package org.junit5.sample;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit5.core.Listener;
import org.junit5.spi.engine.Engine;
import org.junit5.spi.engine.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SampleEngine implements Engine {
    private static final Logger logger = LoggerFactory.getLogger(SampleEngine.class);

    @Override
    public void start(Params params, Listener listener) {
        int counter = 0;
        for (TestInvocation testInvocation : getExecutables(params)) {
            String id = String.valueOf(counter);
            listener.onTestStart(id);

            try {
                testInvocation.execute();
            } catch (Exception e) {
                listener.onTestFailure(id);
            } finally {
                counter++;
                listener.onTestComplete(id);
            }
        }
    }

    Iterable<TestInvocation> getExecutables(Params params) {

        ArrayList<TestInvocation> testInvocations = new ArrayList<>();

        for (Class<?> klass : params.getTestTargets()) {
            for (Method method : klass.getDeclaredMethods()) {
                SampleTest annotation = method.getAnnotation(SampleTest.class);
                if (annotation != null) {
                    Object instance;
                    try {
                        Constructor<?> constructor = klass.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        instance = constructor.newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        logger.warn("Oh no!", e);
                        // TODO ??? call listener maybe?
                        continue;
                    }

                    testInvocations.add(() -> method.invoke(instance));
                }
            }
        }

        return testInvocations;
    }
}
