package code.src.main.java.ch.heig.dai.lab.smtp;


import java.io.*;
import java.nio.charset.*;
import java.sql.SQLData;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class Configuration {
    File victims;
    File messages;
    int numberOfGroups;
    int nbrPeoplePerGroup;

    Configuration(File victims, File messages, int numberOfGroups, int nbrPeoplePerGroup){
        this.victims = victims;
        this.messages = messages;
        if(numberOfGroups < 1){
            throw new IllegalArgumentException("Number of groups inferior to 1");
        }
        this.numberOfGroups = numberOfGroups;
        this.nbrPeoplePerGroup = nbrPeoplePerGroup;
    }

    public String[] readVictims(){
        Charset charset = getEncoding(this.victims);
        String content;
        if(charset != null) {
            content = readFile(this.victims, charset);
            return content.trim().split("\\s*,+\\s*,*\\s*"); // pour éviter les espaces
        }
        return null;
    }

    public String[] readMessages(){
        Charset charset = getEncoding(this.messages);
        String content;
        if(charset != null) {
            content = readFile(this.messages, charset);
            return content.split("---");
        }
        return null;
    }

    public boolean validateEmail(String[] emails){
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        for(int i = 0; i < emails.length; i++) {
            if(!Pattern.compile(regexPattern).matcher(emails[i]).matches()){
                return false;
            }
        }
         return true;
    }

    public String[][] formGroup(String[] group){

        String[][] str = new String[this.numberOfGroups][this.nbrPeoplePerGroup];
        int peopleInserted = 0;
        for(int i = 0; i < this.numberOfGroups; i++){
            for(int j = 0; j < this.nbrPeoplePerGroup; j++){
                if(group.length <= peopleInserted){
                    return str;
                }
                str[i][j] = group[peopleInserted++];

            }
        }
        return str;
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
