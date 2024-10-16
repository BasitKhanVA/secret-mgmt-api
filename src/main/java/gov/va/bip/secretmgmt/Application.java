package gov.va.bip.secretmgmt;

import gov.va.bip.secretmgmt.config.SwaggerConfig;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        checkRequiredEnvVariables();

        ResourceConfig config = new ResourceConfig();
        config.packages("gov.va.bip.secretmgmt.controller");
        config.register(JacksonFeature.class);
        config.register(OpenApiResource.class);
        config.register(SwaggerConfig.class);

        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            logger.info("Starting the server...");
            server.start();
            logger.info("Server started successfully. Listening on port 8080.");
            server.join();
        } catch (Exception ex) {
            logger.error("Error occurred while starting the server", ex);
        }
    }

    private static void checkRequiredEnvVariables() {
        // ... (keep the existing implementation)
    }
}