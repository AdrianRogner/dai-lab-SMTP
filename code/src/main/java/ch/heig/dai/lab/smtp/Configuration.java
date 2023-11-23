package code.src.main.java.ch.heig.dai.lab.smtp;


import java.io.*;
import java.nio.charset.*;
import java.sql.SQLData;
import java.util.ArrayList;

public class Configuration {
    File victims;
    File messages;
    int numberOfGroups;

    Configuration(File victims, File messages, int numberOfGroups){
        this.victims = victims;
        this.messages = messages;
        this.numberOfGroups = numberOfGroups;
    }

    public String[] configureString(File f){
        Charset charset = getEncoding(f);
        String content;
        if(charset != null) {
            content = readFile(f, charset);
            return content.split("$$$");
        }
        return null;
    }

    private String readFile(File file, Charset encoding) {
        try(var reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        encoding));){
            StringBuilder stringbuilder = new StringBuilder();
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                stringbuilder.append(sCurrentLine).append("\n");
            }
            reader.close();
            return stringbuilder.toString();
        }catch(IOException e){
            return null;
        }
    }

    private Charset getEncoding(File file) {
        // TODO: implement the method body here
        String f = file.getName();
        String[] words = f.split("\\.");

        if(words[words.length - 1] != null) {
            switch (words[words.length - 1]) {
                case "utf8":
                    return StandardCharsets.UTF_8;
                case "txt":
                    return StandardCharsets.US_ASCII;
                case "utf16be":
                    return StandardCharsets.UTF_16BE;
                case "utf16le":
                    return StandardCharsets.UTF_16LE;
            }
        }
        return null;
    }
}
