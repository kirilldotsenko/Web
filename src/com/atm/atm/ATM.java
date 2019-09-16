package com.atm.atm;

import com.atm.client.Client;
import com.google.gson.JsonObject;
import org.json.JSONObject;


import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.Random;
import java.net.Socket;

public class ATM{
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static int flagToPass=0;
    private static boolean flagToWork=false;
    final static String url = "jdbc:mysql://localhost/bankomat?serverTimezone=Europe/Moscow&useSSL=false";
    static String username = "";
    static String password = "";
    final static int[] names= new int[] {5000,2000,1000,500,200,100};
    public static void main(String[] args){
        startDB();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter your card for start!");
        sc.nextLine();
        JSONObject req = new JSONObject();
        Client cl=new Client();
        try {
            clientSocket = new Socket("localhost", 4004);


            while(flagToPass<3){
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                System.out.println("Enter your PIN:");
                cl.pincode = sc.nextLine();
                req.put("id", 0);
                req.put("numbercard", cl.numbercard);
                req.put("password",cl.pincode);
                req.put("balance",Double.toString(cl.balance));
                req.put("transCard","");
                req.put("transMoney","");
                out.write(req.toString() + "\n");
                out.flush();

                String answer = in.readLine();

                if(answer.equals("Accept")){
                    flagToWork=true;
                    flagToPass=3;
                    req.put("id",1);
                    out.write(req.toString() + "\n");
                    out.flush();
                    answer = in.readLine();
                    if(answer.equals("Error!")) {
                        System.out.println("Sorry!Try later!");
                        continue;
                    }
                    cl.setBalance(Double.parseDouble(answer));
                }
                else if(answer.equals("Error")){
                    flagToPass++;
                    if(flagToPass==3){
                        break;
                    }
                    System.out.println("Error!Attempt number "+(flagToPass+1));
                }
                while(flagToWork){
                    System.out.print("\nATM Menu: \n \n"
                            + "1. Check Account Balance\n"
                            + "2. Deposit Money \n"
                            + "3. Withdraw Money \n"
                            + "4. Withdraw All Money \n"
                            + "5. Money transfers \n"
                            + "6. End Session\n \n"
                            + "Enter selection: ");
                    int num = sc.nextInt();
                    if(num==1){
                        req.put("id",1);
                        out.write(req.toString() + "\n");
                        out.flush();
                        answer = in.readLine();
                        if(answer.equals("Error!")) {
                            System.out.println("Sorry!Try later!");
                            continue;
                        }
                        cl.setBalance(Double.parseDouble(answer));
                        cl.showBalance();
                    }else if(num==2){
                        System.out.println("Enter your money:");
                        int mon= sc.nextInt();
                        if(mon==0 || mon%100!=0 || mon<100){
                            System.out.println("ERROR!Incorrect input!");
                            continue;
                        }
                        depositDB(mon);
                        cl.deposit(mon);
                        req.put("id",2);
                        req.put("balance",Double.toString(cl.balance));
                        out.write(req.toString() + "\n");
                        out.flush();
                    }else if(num==3){
                        System.out.println("Enter your money:");
                        int mon= sc.nextInt();
                        req.put("id",1);
                        out.write(req.toString() + "\n");
                        out.flush();
                        answer = in.readLine();
                        cl.setBalance(Double.parseDouble(answer));
                        if(mon<100 || mon==0 || mon%100!=0 || mon>=cl.balance){
                            System.out.println("ERROR!Incorrect input!");
                            continue;
                        }
                        int res=withdrawDB(mon);
                        if(res==1){
                            cl.withdraw(Double.valueOf(mon));
                            req.put("id",2);
                            req.put("balance",Double.toString(cl.balance));
                            out.write(req.toString() + "\n");
                            out.flush();
                        }
                    } else if(num==4){
                        req.put("id",1);
                        out.write(req.toString() + "\n");
                        out.flush();
                        answer = in.readLine();
                        cl.setBalance(Double.parseDouble(answer));
                        int mon = cl.getBalance()-(cl.getBalance()%100);
                        int res=withdrawDB(mon);
                        if(res==1){
                            cl.withdraw(mon);
                            req.put("id",2);
                            req.put("balance",Double.toString(cl.balance));
                            out.write(req.toString() + "\n");
                            out.flush();
                        }
                    }else if(num==5){
                        System.out.println("Enter card number:");
                        int numCard= sc.nextInt();
                        sc.nextLine();
                        req.put("id",1);
                        out.write(req.toString() + "\n");
                        out.flush();
                        answer = in.readLine();
                        cl.setBalance(Double.parseDouble(answer));
                        System.out.println("Enter amount of money:");
                        double mon= sc.nextDouble();
                        if(mon==0 || mon>=cl.balance){
                            System.out.println("ERROR!Incorrect input!");
                            continue;
                        }
                        req.put("id",3);
                        req.put("transCard",Integer.toString(numCard));
                        req.put("transMoney",Double.toString(mon).replace(',', '.'));
                        out.write(req.toString() + "\n");
                        out.flush();
                        answer = in.readLine();
                        if(answer.equals("Succesful")){
                            System.out.println(answer);
                            cl.withdraw(mon);
                        }
                        else{
                            System.out.println(answer);
                        }
                    }else if(num==6){
                        req.put("id",4);
                        out.write(req.toString() + "\n");
                        out.flush();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public static void startDB(){
        Random rnd = new Random(System.currentTimeMillis());
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?serverTimezone=Europe/Moscow&useSSL=false", username, password);
                 Statement statement = conn.createStatement()){
                statement.executeUpdate("DROP DATABASE bankomat");
                statement.executeUpdate("CREATE DATABASE bankomat");
                statement.executeUpdate("USE bankomat");
                statement.executeUpdate("DROP TABLE IF EXISTS money");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS money(id MEDIUMINT NOT NULL AUTO_INCREMENT, name INT UNSIGNED NOT NULL, count INT UNSIGNED, PRIMARY KEY(id))");
                for(int i=0;i<names.length;i++){
                    int number = 0 + rnd.nextInt(20 - 0 + 1);
                    statement.executeUpdate("insert into money (name,count) VALUES('"+names[i]+"','"+number+"') ");
                }
                System.out.println("Connection to Store DB succesfull!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
    public static void depositDB(double mon){
        Random rnd = new Random(System.currentTimeMillis());
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement statement = conn.createStatement()){
                int bnotes=0;
                int min=0;
                while(mon>0){
                    int number = min + rnd.nextInt(5 - min + 1);
                    if(mon==100){
                        bnotes++;
                        mon-=100;
                        statement.executeUpdate("UPDATE money SET count=count+1 WHERE name='"+names[5]+"'");
                    }
                    else if(mon>100 && names[number]<=mon){
                        bnotes++;
                        mon-=names[number];
                        statement.executeUpdate("UPDATE money SET count=count+1 WHERE name='"+names[number]+"'");
                        if(mon<200){
                            min++;
                        }
                    }
                }
                System.out.println("\n" +
                        "banknotes deposited:"+bnotes);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static int withdrawDB(int mon){
        HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        int bnotes=0;
        int i=0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement statement = conn.createStatement()){
                int counter=0;
                ResultSet rs=statement.executeQuery("SELECT count FROM money");
                while(rs.next()) {
                    myMap.put(names[counter],rs.getInt(1));
                    counter++;
                }
                while(i<names.length && bnotes<=40 && mon>0){
                    if(mon>=names[i] && myMap.get(names[i])>0 && myMap.get(names[i])>mon/names[i]){
                        int bns=mon/names[i];
                        mon=mon-(names[i]*bns);
                        myMap.put(names[i],myMap.get(names[i])-bns);
                        bnotes+=bns;
                    }
                    else if(mon>=names[i] && myMap.get(names[i])>0 && myMap.get(names[i])<mon/names[i]){
                        mon=mon-(names[i]*myMap.get(names[i]));
                        bnotes+=myMap.get(names[i]);
                        myMap.put(names[i],myMap.get(names[i])*0);
                    }
                    i++;
                }
                if(mon>0 && bnotes<=40){
                    System.out.println("Bankomat haven`t banknotes");
                    return 0;
                }
                else if(mon>0 && bnotes>40)
                {
                    System.out.println("Over 40 banknotes!");
                    return 0;
                }
                for(int k=0;k<myMap.size();k++){
                    statement.executeUpdate("UPDATE money SET count='"+myMap.get(names[k])+"' WHERE name='"+names[k]+"'");
                }
                System.out.println(bnotes);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return 1;
    }
}

