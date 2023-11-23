package code.src.main.java.ch.heig.dai.lab.smtp;
import java.net.*;
import java.io.*;
public class Main {
    public static void main(String[] args){

        if(args.length != 3){
            System.out.println("Pas assez d'arguments");
        } else  {

            for (String s : args) {
                System.out.println(s);
            }
            

        }



    }


}
