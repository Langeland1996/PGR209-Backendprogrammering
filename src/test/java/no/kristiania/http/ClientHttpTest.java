package no.kristiania.http;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientHttpTest {

    @Test
    public void ShouldReadStatusCode() throws IOException {
        ClientHttp client = new ClientHttp("httpbin.org", 80, "/html");
        assertEquals(200, client.getStatus());
    }


    @Test
    public void ShouldNotReadStatusCode() throws IOException {
        ClientHttp client = new ClientHttp("httpbin.org", 80, "/non-existing path");
        assertEquals(404, client.getStatus());
    }

    @Test
    public void ShouldReadGetHeader() throws IOException {
        ClientHttp client = new ClientHttp("httpbin.org", 80, "/html");
        assertEquals("text/html; charset=utf-8", client.getHeader("Content-Type"));
        assertEquals("3741", client.getHeader("Content-Length"));
        assertEquals("gunicorn/19.9.0", client.getHeader("Server"));
    }

}