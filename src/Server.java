import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket servSocket = new ServerSocket(8080);
        FibonachchiTask fbt = new FibonachchiTask();
        try {
            while (true) {
                Socket socket = servSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    out.println("Answer is: " + fbt.getFibonacciValue(Integer.parseInt(line)));
                    if (line.equals("end")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            servSocket.close();
        }
    }
}