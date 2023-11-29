package ch.heig.dai.lab.smtp;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {

        SmtpClient client = new SmtpClient();
        client.sendEmail();
    }
}
