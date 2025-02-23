package ch.heigvd.api.calc;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calculator client implementation
 */
public class Client {

    private static final Logger LOG = Logger.getLogger(Client.class.getName());

    /**
     * Main function to run client
     *
     * @param args no args required
     */
    public static void main(String[] args) {
        // Log output on a single line
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        BufferedReader stdin;

        /* TODO: Implement the client here, according to your specification
         *   The client has to do the following:
         *   - connect to the server
         *   - initialize the dialog with the server according to your specification
         *   - In a loop:
         *     - read the command from the user on stdin (already created)
         *     - send the command to the server
         *     - read the response line from the server (using BufferedReader.readLine)
         */

        BufferedReader is;
        BufferedWriter os;
        Socket clientSocket;

        try {
            clientSocket = new Socket("10.191.4.112", 7777);
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));
            stdin = new BufferedReader(new InputStreamReader(System.in));

            String open = "open\n";
            os.write(open);
            os.flush();

            String serverResponse = is.readLine();
            System.out.println(serverResponse);

            if(!serverResponse.equals("connection ok")){throw new RuntimeException("connection failed");}

            String client;
            System.out.println("Bonjour :");
            do{
                client = stdin.readLine();
                os.write(client + "\n");
                os.flush();
                if(!Objects.equals(client, "close")){
                    serverResponse = is.readLine();
                    System.out.println(serverResponse);
                }
            }while(!client.equals("close"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
