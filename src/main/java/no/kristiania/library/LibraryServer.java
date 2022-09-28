package no.kristiania.library;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibraryServer {
    private static final Logger logger = LoggerFactory.getLogger(LibraryServer.class);

    private final Server server = new Server(9080);

    public void start() throws Exception {
        server.setHandler(new WebAppContext(
                Resource.newClassPathResource("/webapp"),
                "/"
        ));

        server.start();
        logger.info("Starter servcer on {}", server.getURI());
    }

    public static void main(String[] args) throws Exception {
        new LibraryServer().start();
    }
}
