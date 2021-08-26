package vn.vnpay;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import vn.vnpay.constant.SystemConstant;
import vn.vnpay.controller.MerchantController;
import vn.vnpay.utils.JedisPoolUtils;

import java.net.URI;

public class JettyServer {

    private static final Logger logger = LogManager.getLogger(JettyServer.class);

    public static Server startServer() {
        logger.info("<------Starting project!!!----->");
        final ResourceConfig resourceConfig = new ResourceConfig(MerchantController.class);
        return JettyHttpContainerFactory.createServer(URI.create(SystemConstant.BASE_URI), resourceConfig);
    }

    public static void main(String[] args ) {
        try {
            final Server server = startServer();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    logger.warn("Shutting down the application...");
                    server.stop();

                    logger.warn("Shutting down pool....");
                    JedisPoolUtils.getInstance().closePool();

                    logger.info("<---- Done, exit ---->");
                } catch (Exception e) {
                    logger.error("Fail to shutting down server", e);
                }
            }));

            logger.info("Application started. Stop the application using CTRL+C");
            Thread.currentThread().join();

        } catch (InterruptedException ex) {
            logger.error("Fail to start server: ", ex);
        }

    }
}

