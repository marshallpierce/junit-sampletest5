import java.util.ArrayList;
import org.junit5.Launcher;
import org.junit5.core.Listener;
import org.junit5.spi.engine.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class LauncherMain {
    public static void main(String[] args) throws ClassNotFoundException {

        ArrayList<Class<?>> objects = new ArrayList<>();
        objects.add(Class.forName("org.mpierce.testing.AwesomeTest"));
        new Launcher().launch(new DummyListener(), new Params(objects));
    }

    static class DummyListener implements Listener {
        private static final Logger logger = LoggerFactory.getLogger(DummyListener.class);

        @Override
        public void onTestStart(String id) {
            logger.info("Started: " + id);
        }

        @Override
        public void onTestComplete(String id) {
            logger.info("Completed: " + id);
        }

        @Override
        public void onTestFailure(String id) {
            logger.info("Failed: " + id);
        }
    }
}
