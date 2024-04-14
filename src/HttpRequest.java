import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequest {
    private String method;
    private String url;

    public HttpRequest(Socket socket) throws IOException {
        readClientRequest(socket);
    }

    private void readClientRequest(Socket socket) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String request = input.readLine();
        if(request != null && !request.isEmpty()) {
            String[] request_parts = request.split(" ");
            method = request_parts[0];
            url = request_parts[1];
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
