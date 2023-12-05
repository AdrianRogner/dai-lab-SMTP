package ch.heig.dai.lab;

import ch.heig.dai.lab.network.SmtpClient;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {

        SmtpClient client = new SmtpClient();
        client.sendEmail();

    }
}
