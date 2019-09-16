package com.atm.card;


import java.io.*;
import java.io.Serializable;

public class Card implements Serializable{
    public static String numcard;
    public Card(){
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Kirill\\Desktop\\ATMproject\\src\\com\\atm\\card\\1.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                numcard= line;
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

