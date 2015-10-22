package org.junit5.spi.engine;

public final class Params {

    private final Iterable<Class<?>> objects;

    public Params(Iterable<Class<?>> objects) {
        this.objects = objects;
    }

    public Iterable<Class<?>> getTestTargets() {
return objects;
    }
}
