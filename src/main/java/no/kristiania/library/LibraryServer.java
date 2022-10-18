package no.kristiania.library;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class LibraryServer {
    private static final Logger logger = LoggerFactory.getLogger(LibraryServer.class);
    private final Server server;

    public LibraryServer(int port) {

        this.server = new Server(port);

        // content itself
        WebAppContext webContext = new WebAppContext();
        // what path the content should be placed
        webContext.setContextPath("/");
        // where we get the content from
        webContext.setBaseResource(Resource.newClassPathResource("/webapp"));
        // deliver content
        server.setHandler(webContext);
    }

    public URL getURL() throws MalformedURLException {
        return server.getURI().toURL();
    }

    public void start() throws Exception {
        server.start();
        logger.info("Server starting at {}", this.getURL());

    }

    public static void main(String[] args) throws Exception {
        var server = new LibraryServer(8080);
        server.start();

    }
}
