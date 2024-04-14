import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer{
//    private void readRequest(Socket socket){
//        try {
//            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String response = "";
//            while((response = input.readLine()) != null && !response.isEmpty()) {
//                System.out.println(response);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private void sendResponse(Socket socket) {
//        try {
//            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            String response = "HTTP/1.1 200 OK\r\n"+
//                    "Content-type: text/html\r\n\r\n"+
//                    "Success !!!!";
//            output.write(response);
//            output.flush();
//            output.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void run(int portNumber){
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while(true)
            {
                Socket clientSocket = serverSocket.accept();
                RequestProcessor request = new RequestProcessor(clientSocket);
                Thread thread = new Thread(request);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}