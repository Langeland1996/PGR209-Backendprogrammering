package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;

public class ClientHttp {
    public static void main(String[] args) throws IOException {
        //ClientHttp client = new ClientHttp("httpbin.org", 80, "/html");
    }

    private int statusCode;

    public ClientHttp(String host, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(host, port);

        socket.getOutputStream().write(
                ("GET "+requestTarget+" HTTP/1.1\r\n" +
                        "Connection: close\r\n" +
                        "Host: "+host+"\r\n" +
                        "\r\n").getBytes());


        StringBuilder line = readLine(socket);
        statusCode = Integer.parseInt(line.toString().split(" ")[1]);
    }

    private StringBuilder readLine(Socket socket) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while ((c = socket.getInputStream().read()) != -1){
            line.append((char)c);
        }
        System.out.println(line);

        return line;
    }

    public int getStatus() {
        return statusCode;
    }
}
