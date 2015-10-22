package org.junit5;

import java.util.ServiceLoader;
import org.junit5.core.Listener;
import org.junit5.spi.engine.Engine;
import org.junit5.spi.engine.Params;

public final class Launcher {

    public void launch(Listener listener, Params params) throws ClassNotFoundException {
        ServiceLoader<Engine> loader = ServiceLoader.load(Engine.class);

        for (Engine engine : loader) {
            engine.start(params, listener);
        }
    }
}
