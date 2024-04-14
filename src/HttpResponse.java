import java.io.*;
import java.net.Socket;

public class HttpResponse {
    private OutputStream output;

    public HttpResponse(Socket socket) throws IOException {
        output = socket.getOutputStream();
    }

    public void ok(String message) throws IOException {
        String response = "HTTP/1.0 200 "+ message +"\r\n";
        output.write(response.getBytes());
        output.flush();
    }

    public void notFound(String message) throws IOException {
        String response = "HTTP/1.0 404 "+ message +"\r\n";
        output.write(response.getBytes());
        output.flush();
    }

    public void sendContent(String contentType, String content) throws IOException {
        String response = "Content-type: "+ contentType +"\r\n\r\n"+content;
        output.write(response.getBytes());
        output.flush();
    }

    public void sendFile(String contentType, String fileName) throws IOException {
        String response = "Content-type: "+ contentType +"\r\n\r\n";
        output.write(response.getBytes());

        if((new File("public"+fileName)).exists()) {
            FileInputStream file = new FileInputStream("public"+fileName);
            byte[] bytes = new byte[4096];
            int bytesRead = 0;
            do {
                bytesRead = file.read(bytes);
                output.write(bytes, 0, bytesRead);
            } while (bytesRead == 4096);
        }
        output.flush();
    }
}
