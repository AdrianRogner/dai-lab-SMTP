package ch.heig.dai.lab.smtp;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {

<<<<<<< HEAD
        SmtpClient client = new SmtpClient();
        client.sendEmail();
=======
        if(args.length != 3){
            System.out.println("Pas assez d'arguments");
        } else  {

            for (String s : args) {
                System.out.println(s);
            }
            

        }



>>>>>>> 38b6d70a9e8e334797cf5b7da150c5bf87eea6da
    }


}
