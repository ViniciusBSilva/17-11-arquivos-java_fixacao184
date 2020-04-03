package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import model.entities.Order;
import model.entities.Product;
import model.exceptions.DomainException;

public class Program {

	public static final String INPUT_FILE_PATH = ".\\testfiles\\";
	public static final String INPUT_FILE_NAME = "source.csv";

	public static final String OUTPUT_FILE_PATH = ".\\testfiles\\out\\";
	public static final String OUTPUT_FILE_NAME = "_summary.csv";

	public static final String SEPARATOR = ";";

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		String inputPath = INPUT_FILE_PATH + INPUT_FILE_NAME;
		
		Order order = new Order();

		// Try-with-resources!!!
		try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) { 

			System.out.printf("%nFile %s read%n", inputPath);
			
			String line = br.readLine();
			while (line != null) {

				String[] splitedLine = new String[3];
				splitedLine = line.split(SEPARATOR, 3);

				String productName = splitedLine[0];
				Double itemValue = Double.valueOf(splitedLine[1]);
				Integer quantity = Integer.valueOf(splitedLine[2]);

				order.addItem(productName, itemValue, quantity);

				line = br.readLine();
			}	
			

		} catch (FileNotFoundException e) {
			System.out.println("File Error: " + e.getMessage());
		} catch (DomainException e) {
			System.out.println("Domain Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		}
		
		// Print order before send it to file
		System.out.println(order);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String dateString = sdf.format(new Date());
		
		String outputPath = OUTPUT_FILE_PATH + dateString + OUTPUT_FILE_NAME;
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
			
			List<Product> products = order.getProducts();
			
			for (Product product : products) {
				
				StringBuilder sb = new StringBuilder();
				
				sb.append(product.getName());
				sb.append(SEPARATOR);
				sb.append(product.getPrice());
				
				bw.write(sb.toString());
				bw.newLine();
				
			}
			
			System.out.printf("%nFile %s created%n", outputPath);
			
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		}

	}

}
