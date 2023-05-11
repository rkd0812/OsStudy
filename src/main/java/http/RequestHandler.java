package http;

import java.io.*;
import java.net.Socket;

public class RequestHandler extends Thread {

    private Socket connection;

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try (InputStream in = connection.getInputStream();
             OutputStream out = connection.getOutputStream()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = br.readLine();
            if (line == null) {
                return;
            }

            String[] tokens = line.split(" ");

            for(String token : tokens) {
                System.out.println("token:: " +token);
            }

            DataOutputStream dos = new DataOutputStream(out);
            byte[] body = "Hello World".getBytes();

            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + body.length + "\r\n");
            dos.writeBytes("\r\n");

            dos.write(body, 0, body.length);
            dos.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
