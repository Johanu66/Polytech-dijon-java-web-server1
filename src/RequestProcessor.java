import java.io.IOException;
import java.net.Socket;

public class RequestProcessor implements Runnable {
    private HttpContext context;

    public RequestProcessor(Socket socket) throws IOException {
        this.context = new HttpContext(socket);
    }

    @Override
    public void run() {
        try {
            process();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void process() throws IOException {
        HttpRequest request = context.getHttpRequest();
        HttpResponse response = context.getHttpResponse();

        String url = request.getUrl();
        String method = request.getMethod();
        System.out.println("Request URL: " + url);
        System.out.println("Request Method: " + method + "\n\n");

        String contentType;
        if(url != null && !url.isEmpty()) {
            response.ok("Success !!!");
            if(url.equals("/")) {
                url = "/index.html";
                contentType = "text/html";
            } else if (url.endsWith(".html")) {
                contentType = "text/html";
            } else if (url.endsWith(".css")) {
                contentType = "text/css";
            } else if (url.endsWith(".jpg") || url.endsWith(".jpeg")) {
                contentType = "image/jpeg";
            } else if (url.endsWith(".png")) {
                contentType = "image/png";
            } else if (url.endsWith(".webp")) {
                contentType = "image/webp";
            } else if (url.endsWith(".svg")) {
                contentType = "image/svg+xml";
            } else if (url.endsWith(".ico")) {
                contentType = "image/vnd.microsoft.icon";
            } else if (url.endsWith(".mp4")) {
                contentType = "video/mp4";
            } else {
                contentType = "application/octet-stream";
            }
            response.sendFile(contentType, url);
        }else {
            response.notFound("Not Found !!!");
        }
        context.close();
    }
}
