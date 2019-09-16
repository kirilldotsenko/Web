package com.atm.client;

import com.atm.card.Card;

public class Client{
    public static double balance;
    public static String numbercard;
    public static String pincode;
    private Card clientCard;

    public Client(){
        this.clientCard=new Card();
        this.numbercard=clientCard.numcard;
    }

    public static void setBalance(double bal){
        balance=bal;
    }

    public static void deposit(int dep){
        String k=String.format("%.2f", dep+balance).replace(',', '.');
        System.out.println(k);
        balance=Double.parseDouble(k);
        System.out.print("Your balance: "+balance);
    }

    public static void withdraw(double wit){
        String k=String.format("%.2f", balance-wit).replace(',', '.');
        System.out.println(k);
        balance=Double.parseDouble(k);
        System.out.print("Your balance: "+balance);
    }
    public static void showBalance(){
        System.out.println(balance);
    }
    public static int getBalance(){
        System.out.println(numbercard);
        return (int) balance;
    }



}
