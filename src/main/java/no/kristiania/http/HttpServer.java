package no.kristiania.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    private ServerSocket serverSocket;

    public HttpServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        start();
    }

    private void start() {
        new Thread(() -> {
            try {
                var clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        System.out.println("Server started");
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");

        var serverSocket = new ServerSocket(9080);

        var clientSocket = serverSocket.accept();

        var request = new HttpMessage(clientSocket);
        System.out.println(request.getStartLine());
        System.out.println(request.headers);

        var body = "<html><h1>Hello World!</h1></html>";
        var contentLength = body.getBytes().length;
        clientSocket.getOutputStream().write(("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + contentLength + "\r\n" +
                "\r\n" +
                body).getBytes(StandardCharsets.UTF_8));
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        clientSocket.getOutputStream().write(("HTTP/1.1 404 NOT FOUND\r\n" +
                "\r\n").getBytes(StandardCharsets.UTF_8));
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }
}
