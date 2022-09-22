package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class HttpRequestResult {

    private HttpMessage response;
    private int statusCode;

    public HttpRequestResult(String host, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(host, port);

        socket.getOutputStream().write(
                ("GET "+requestTarget+" HTTP/1.1\r\n" +
                        "Connection: close\r\n" +
                        "Host: "+host+"\r\n" +
                        "\r\n").getBytes(StandardCharsets.UTF_8));
        response = new HttpMessage(socket);
        statusCode = Integer.parseInt(response.getStartLine().split(" ")[1]);
    }



    public int getStatus() {
        return statusCode;
    }

    public String getHeader(String fieldName){
        return response.getHeader(fieldName);
    }

    public int getContentLength() {
        return response.contentLength;
    }

    public String getBody() {
        return response.body;
    }
}
