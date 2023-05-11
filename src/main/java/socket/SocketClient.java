package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws Exception {
        connectServer();
    }

    public static void connectServer() throws Exception {

        try (Socket socket =  new Socket("127.0.0.1", 8000)) {
            OutputStream out = socket.getOutputStream();
            String outMsg = "JJI is pretty~!!!";
            System.out.println("To Server : " + outMsg);
            out.write(outMsg.getBytes());
            out.flush();

            InputStream is = socket.getInputStream();
            byte[] input = new byte[100];
            int length = is.read(input);
            String inputData = new String(input, 0, length);
            System.out.println("From Server : " + inputData);
        }
    }
}
