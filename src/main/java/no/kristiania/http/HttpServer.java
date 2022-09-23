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

        handleClient(clientSocket);
        var request = new HttpMessage(clientSocket);
        System.out.println(request.getStartLine());
        System.out.println(request.headers);

    }

    private static void handleClient(Socket clientSocket) throws IOException {
        var request = new HttpMessage(clientSocket);
        System.out.println(request.getStartLine());
        var requestTarget = request.getStartLine().split(" ")[1];
        var responseBody = "Unknown URL '" + requestTarget + "'";

        clientSocket.getOutputStream().write(("HTTP/1.1 404 NOT FOUND\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: " + responseBody.length() + "\r\n" +
                "\r\n" +
                responseBody +
                "\r\n").getBytes(StandardCharsets.UTF_8));
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }
}
