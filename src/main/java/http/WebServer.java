package http;

import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) throws Exception {
        startup();
    }

    private static void startup() throws Exception {
        int port = 8080;

        try (ServerSocket listenSocket = new ServerSocket(port)) {
            Socket connection;

            while ((connection = listenSocket.accept()) != null) {
                RequestHandler requestHandler = new RequestHandler(connection);
                requestHandler.start();
            }
        }
    }
}
