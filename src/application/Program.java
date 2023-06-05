package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		String path = "C://temp//ex034.csv";
		
		try(BufferedReader br =  new BufferedReader(new FileReader(path))) {
			List<Product> list = new ArrayList<>();
			
			String line = br.readLine();
			
			
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));
				
				line = br.readLine();
			}
			
			double amount = list.stream()
					.mapToDouble(Product::getPrice)
	                .sum();
			double avg = amount / list.size();
			
			list.removeIf(p -> p.getPrice() > avg);
			
			list.sort((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()));
			
			for (Product p : list) {
				System.out.println(p);
			}			
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}

}
