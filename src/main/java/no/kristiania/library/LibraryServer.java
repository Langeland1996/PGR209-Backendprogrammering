package no.kristiania.library;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.MalformedURLException;
import java.net.URL;

public class LibraryServer {

    private final Server server;

    public LibraryServer(int port) {

        this.server = new Server(port);

        // context itself
        WebAppContext webContext = new WebAppContext();
        // what path the context should be placed
        webContext.setContextPath("/");
        // where we get the context from
        webContext.setBaseResource(Resource.newClassPathResource("/webapp"));
        // deliver context
        server.setHandler(webContext);
    }

    public URL getURL() throws MalformedURLException {
        System.out.println("\n===============================================");
        System.out.println("Server live URL --> " + server.getURI().toURL());
        System.out.println("===============================================");
        return server.getURI().toURL();
    }

    public void start() throws Exception {
        server.start();

    }
}
