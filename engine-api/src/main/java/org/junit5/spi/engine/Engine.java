package org.junit5.spi.engine;

import org.junit5.core.Listener;

public interface Engine {

    void start(Params params, Listener listeners);
}
