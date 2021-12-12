package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class NBPconnector {
    public static final String API_URL = "http://api.nbp.pl/api/exchangerates/rates/";

    public String getCurrencyData(String tableName, String currencySymbol) throws IOException{

            URL url = new URL(API_URL + tableName +"/" + currencySymbol);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println("Connected to NBP");

            int status = con.getResponseCode();
            System.out.println("Status: " + status);

            BufferedReader in = new BufferedReader( //for efficiency
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
    }

    public Double getCurrencyPrice(String tableName, String currencySymbol)throws IOException{
        String data = getCurrencyData(tableName, currencySymbol);
        JSONTokener tokener = new JSONTokener(data);
        JSONObject currencyObject = new JSONObject(tokener);

        String table = currencyObject.getString("table");
        String currency = currencyObject.getString("currency");
        String code = currencyObject.getString("code");
        JSONArray rates = currencyObject.getJSONArray("rates");
        Double price = rates.getJSONObject(0).getDouble("mid");
        //System.out.println(currencyObject.getString("currency"));
        return price;
    }
}
