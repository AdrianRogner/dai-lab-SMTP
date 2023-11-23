package ch.heig.dai.lab.smtp;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import ch.heig.dai.lab.smtp.*;
public class TcpClientApplication {
    private final String SERVER_ADDRESS = "localhost";
    private final int SERVER_PORT = 1025;
    private Socket socket = null;
    private BufferedReader input = null;
    private BufferedWriter output = null;
    private final Charset CHARSET = StandardCharsets.UTF_8;

    public TcpClientApplication(){}
    /**
     * @brief Opens a connection to the server.
     */
    public void openConnection() throws IOException {
        try {
            // Create a socket to connect to the server at the specified address and port.
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            // Set up input and output streams for communication with the server.
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), CHARSET));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), CHARSET));

            // Display a message indicating a successful connection.
            System.out.println("Connection to server open on port :" + SERVER_PORT);
            String info;
            while ((info = input.readLine()) != null) {
                System.out.println(info);
                if(info.contains("220")) {
                    break;
                }
            }
        } catch (IOException e) {
            // Handle any exceptions that may occur during connection.
            System.out.println("Client: exc.: " + e);
        }
    }

    /**
     * @brief Sends a request to the server and receives responses.
     */
    public void sendRequest() throws IOException {
        try {
            // Continuously prompt the user for commands and send them to the server.
            while (true) {
                String command = "ehlo localhost";
                output.write(command + "\n");
                output.flush();
                System.out.println(command);
                // Display the response from the server.
                String info;
                while ((info = input.readLine()) != null) {
                    System.out.println(info);
                    if(info.contains("250 SMTPUTF8")) {
                        break;
                    }
                }
                break;
            }
        } catch(IOException e){
            // Handle any exceptions that may occur during communication.
            System.out.println("Client: exc.: " + e);
        }
    }
}
