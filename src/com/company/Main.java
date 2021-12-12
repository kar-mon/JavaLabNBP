package com.company;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NBPconnector connector = new NBPconnector();
        Scanner scan = new Scanner(System.in);
        String content;
        String currencySymbol;

        do {
            System.out.println("Type the symbol of currency to get the data or exit");
            currencySymbol = scan.nextLine();
            System.out.println("You entered " + currencySymbol);

            try {
                Double price = connector.getCurrencyPrice("A", currencySymbol);
                System.out.println(price);
            } catch (IOException e) {
                System.out.println("There is no currency as "+currencySymbol);
            }
        } while (!currencySymbol.equals("exit"));

        // try{
        //     content = connector.getCurrencyData("A","EUR");
        //     System.out.println(content);
        // }catch (IOException e){
        //     System.out.println("sorry, there was a problem");
        //     System.out.println("try again later");
        // }



    }
}


