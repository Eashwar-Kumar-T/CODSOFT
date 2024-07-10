package project;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import org.json.*;
public class Currency_converter {
	public static void converter(String from,String to,double amount) throws IOException, JSONException {
			String url_str = "https://v6.exchangerate-api.com/v6/4c7415e377d8556029937de8/latest/"+from;
			@SuppressWarnings("deprecation")
			URL url = new URL(url_str);	
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.setRequestMethod("GET");
			int responseCode=request.getResponseCode();
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();
				while((line = in.readLine())!=null) {
					response.append(line);
				}
				in.close();
				
				JSONObject obj = new JSONObject(response.toString());
				double exchange_rates = obj.getJSONObject("conversion_rates").getDouble(to);
				System.out.println();
				System.out.println("Exchanged "+amount+" "+from+" to "+amount*exchange_rates+" "+to);
			}
			else {
				System.out.println("GET request failed");
			}
	}
	public static void main(String []args) throws IOException, JSONException {
		HashMap<Integer,String> Currency = new HashMap<Integer,String>();
		Currency.put(1,"INR");
		Currency.put(2,"USD");
		Currency.put(3,"CAD");
		Currency.put(4,"JPY");
		Currency.put(5,"PKR");
		Currency.put(6,"LKR");
		Currency.put(7,"GBP");
		Currency.put(8,"PLN");
		Currency.put(9,"MYR");
		Currency.put(10,"AED");
		
		String f , t;
		double am;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Java Currency Converter");
		System.out.println();
		System.out.println("Select which currency to convert FROM?");
		System.out.println("1.INR 2.USD 3.CAD 4.JPY 5.PKR 6.LKR 7.GBP 8.PLN 9.MYR 10.AED");
		
		f=Currency.get(sc.nextInt());
		
		System.out.println("Select which currency to convert TO?");
		System.out.println("1.INR 2.USD 3.CAD 4.JPY 5.PKR 6.LKR 7.GBP 8.PLN 9.MYR 10.AED");
		
		t=Currency.get(sc.nextInt());
		
		System.out.println("Enter the amount to be converted: ");
		am = sc.nextDouble();
		
		converter(f,t,am);
	}
}
