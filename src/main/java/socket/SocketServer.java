package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws Exception {
        startServer();
    }

    private static void startServer() throws Exception {

        try (
                ServerSocket serverSocket = new ServerSocket(8000);
                Socket socket = serverSocket.accept()
        ) {
            InputStream is = socket.getInputStream();
            byte[] input = new byte[100];
            int length = is.read(input);
            String inputData = new String(input, 0, length);
            System.out.println("From Client" +inputData);

            OutputStream out = socket.getOutputStream();
            out.write("Nope~!!!".getBytes());
            out.flush();

        }
    }
}
