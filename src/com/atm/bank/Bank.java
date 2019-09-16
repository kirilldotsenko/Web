package com.atm.bank;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import com.atm.encryptor.*;
import org.json.JSONObject;

public class Bank {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;
    final private static String url = "jdbc:mysql://localhost/bank?serverTimezone=Europe/Moscow&useSSL=false";
    private static String username = "";
    private static String password = "";
    public static void main(String[] args){
        startDB();

        try  {
            server = new ServerSocket(4004);
            System.out.println("Сервер запущен!");

            clientSocket = server.accept();
                try {
                    while(true) {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                        String word = in.readLine();
                        JSONObject jsonObj = new JSONObject(word);
                        Integer id = (Integer) jsonObj.get("id");
                        String password = (String) jsonObj.get("password");
                        String numbercard =(String) jsonObj.get("numbercard");
                        String balance =(String) jsonObj.get("balance");
                        String transCard = (String) jsonObj.get("transCard");
                        String transMoney =(String) jsonObj.get("transMoney");
                        if(id==0){
                            if (checkPass(password, numbercard)) {
                                String answer="Accept";
                                out.write(answer+"\n");
                                out.flush();
                                while(true){
                                    word=in.readLine();
                                    jsonObj = new JSONObject(word);
                                    id= (Integer) jsonObj.get("id");
                                    password = (String) jsonObj.get("password");
                                    numbercard = (String) jsonObj.get("numbercard");
                                    balance =(String) jsonObj.get("balance");
                                    transCard = (String) jsonObj.get("transCard");
                                    transMoney =(String) jsonObj.get("transMoney");
                                    if(id==1 && checkPass(password, numbercard)){
                                        answer=balanceAcc(numbercard);
                                        if(answer!=null){
                                            out.write(answer+"\n");
                                            out.flush();
                                        }else{
                                            out.write("Error!"+"\n");
                                            out.flush();
                                            logFile("Error!",numbercard);//ф-я записи в лог
                                        }
                                    }else if(id==2 && checkPass(password, numbercard)){
                                        boolean ans=updateDB(balance,numbercard);
                                        if (ans){
                                            continue;
                                        }else{
                                            logFile("Error!",numbercard);//ф-я записи в лог
                                        }
                                    }else if(id==3 && checkPass(password, numbercard)){
                                        String balTrans=Double.toString(Double.parseDouble(balanceAcc(transCard))+Double.parseDouble(transMoney));
                                        boolean ans=updateDB(balTrans,transCard);
                                        if (ans){
                                            out.write("Succesful"+"\n");
                                            out.flush();
                                            continue;
                                        }else{
                                            out.write("Error!"+"\n");
                                            out.flush();
                                            logFile("Error!",numbercard);//ф-я записи в лог
                                        }
                                    }else if(id==4 && checkPass(password, numbercard)) {
                                        break;
                                    }
                                }
                            } else {
                                String answer="Error";
                                out.write(answer+"\n");
                                System.out.println("kek");
                                out.flush();
                            }
                        }else {
                            break;
                        }
                    }

                }  catch (IOException e) {
                    System.err.println(e);
                }
        } catch (IOException e) {
        System.err.println(e);
        }
    }
    public static void startDB(){
        final String numCard[]=new String[]{"1","23","51","234","123"};
        final String pin[]=new String[]{"1234","8935","3982","93298","29384"};
        final String name[]=new String[]{"Kirill","Masha","Petya","Julia","Andrey"};
        final int bankAcc[]=new int[]{638,617,783,741391,192387};
        final String balance[]=new String[]{"26729.42","43465.74","76345.87","412941.32","625751.34"};
        Random rnd = new Random(System.currentTimeMillis());
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?serverTimezone=Europe/Moscow&useSSL=false", username, password);
                 Statement statement = conn.createStatement()){
                statement.executeUpdate("DROP DATABASE bank");
                statement.executeUpdate("CREATE DATABASE bank");
                statement.executeUpdate("USE bank");
                statement.executeUpdate("DROP TABLE IF EXISTS cards");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS cards(numCard VARCHAR(20),pin TEXT, PRIMARY KEY(numCard))");
                for(int i=0;i<numCard.length;i++){
                    statement.executeUpdate("insert into cards(numCard,pin) VALUES('"+numCard[i]+"','"+Encryptor.enc(pin[i])+"')");
                }
                statement.executeUpdate("DROP TABLE IF EXISTS clients");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS clients(numCard VARCHAR(20),name VARCHAR(10), bankAcc INT UNSIGNED NOT NULL,balance VARCHAR(15), FOREIGN KEY (numCard) REFERENCES cards(numCard))");
                for(int i=0;i<name.length;i++){
                    statement.executeUpdate("insert into clients (numCard,name,bankAcc,balance) VALUES('"+numCard[i]+"','"+name[i]+"','"+bankAcc[i]+"','"+balance[i]+"') ");
                }
                System.out.println("Connection to Store DB succesfull!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static boolean checkPass(String pass, String numbercard){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement statement = conn.createStatement()) {
                ResultSet rs=statement.executeQuery("SELECT pin FROM cards NATURAL JOIN clients WHERE numCard='"+numbercard+"'");
                while(rs.next()) {
                    if(rs.getString(1).equals(Encryptor.enc(pass))){
                        return true;
                    }
                }
            }
            }
            catch(Exception ex){
                System.out.println("Connection failed...");
                logFile(ex.getMessage(),numbercard);
                System.out.println(ex);
            }
        return false;
    }
    public static String balanceAcc(String numbercard){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement statement = conn.createStatement()) {
                ResultSet rs=statement.executeQuery("SELECT balance FROM cards NATURAL JOIN clients WHERE numCard='"+numbercard+"'");
                while(rs.next()) {
                    String kek=rs.getString(1);
                    return kek;
                }
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            logFile(ex.getMessage(),numbercard);
            System.out.println(ex);
        }
        return null;
    }

    public static boolean updateDB(String bal, String numbercard){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement statement = conn.createStatement()) {
                statement.executeUpdate("UPDATE clients SET balance='"+bal+"' WHERE numCard='"+numbercard+"'");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            logFile(ex.getMessage(),numbercard);
            System.out.println(ex);
        }
        return true;
    }


    public static void logFile(String mes,String numbercard)  {
        String filePath = "C:\\Users\\Kirill\\Desktop\\ATMproject\\src\\com\\atm\\bank\\logfile.txt";
        String text =mes+" "+numbercard+"\n";

        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
