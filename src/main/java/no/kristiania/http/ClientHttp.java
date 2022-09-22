package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ClientHttp {
    public static void main(String[] args) throws IOException {
        //ClientHttp client = new ClientHttp("httpbin.org", 80, "/html");
    }

    private int statusCode;
    private final Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private int contentLength;
    private String body;

    public ClientHttp(String host, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(host, port);

        socket.getOutputStream().write(
                ("GET "+requestTarget+" HTTP/1.1\r\n" +
                        "Connection: close\r\n" +
                        "Host: "+host+"\r\n" +
                        "\r\n").getBytes());


        String line = readLine(socket);
        statusCode = Integer.parseInt(line.split(" ")[1]);

        String headerLine;
        while(!(headerLine = readLine(socket)).isEmpty()){
            String[] parts = headerLine.split(":\\s*");
            headers.put(parts[0], parts[1]);
        }

        contentLength = Integer.parseInt(getHeader("Content-Length"));
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < contentLength; i++){
            body.append((char)socket.getInputStream().read());
        }
        this.body = body.toString();
    }

    private String readLine(Socket socket) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while ((c = socket.getInputStream().read()) != '\r'){
            line.append((char)c);
        }
        c = socket.getInputStream().read();
        System.out.println(line);

        return line.toString();
    }

    public int getStatus() {
        return statusCode;
    }

    public String getHeader(String fieldName){
        return headers.get(fieldName);
    }

    public int getContentLength() {
        return contentLength;
    }

    public String getBody() {
        return body;
    }
}
