package no.kristiania.http;

import org.junit.Test;

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
}