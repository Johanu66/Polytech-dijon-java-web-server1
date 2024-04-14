import java.io.IOException;
import java.net.Socket;

public class HttpContext {
    private Socket socket;
    private HttpRequest request;
    private HttpResponse response;

    public HttpContext(Socket socket) throws IOException {
        this.socket = socket;
        this.request = new HttpRequest(this.socket);
        this.response = new HttpResponse(this.socket);
    }

    public HttpRequest getHttpRequest() {
        return request;
    }

    public HttpResponse getHttpResponse() {
        return response;
    }

    public void close() throws IOException {
        socket.close();
    }
}
