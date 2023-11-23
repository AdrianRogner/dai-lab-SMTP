package ch.heig.dai.lab.smtp;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {

        TcpClientApplication client = new TcpClientApplication();
        client.openConnection();
        client.sendRequest();
    }
}
