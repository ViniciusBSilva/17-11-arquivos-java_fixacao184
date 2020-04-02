package model.entities;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import model.exceptions.DomainException;

public class Order {

	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public Order() {
	}
	
	public void addItem (String productName, Double itemValue, Integer quantity) throws DomainException {
		OrderItem orderItem = new OrderItem (productName, itemValue, quantity); 
		orderItems.add(orderItem);
	}

	public List<Product> getProducts () {
		List<Product> products = new ArrayList<Product>();
		for (OrderItem item : orderItems) {
			products.add(item.getProduct());
		}
		return products;
	}
	
	public Double calculateTotal() {
		Double sum = 0.00;
		for (OrderItem item : orderItems) {
			sum += item.calculateItemTotal();
		}
		return sum;
	}
	
	public String toString () {
		Formatter formatter = new Formatter();
			
		formatter.format("| Item | Product%-33s | Price%-10.2f | Quantity | Item Total%-5.2f | %n", "", "", "", "");
		int i = 0;
		for (OrderItem item : orderItems) {
			i++;
			formatter.format("| %-4s | %-40s | %-15.2f | %-8d | %-15.2f | %n", 
					i, 
					item.getProduct().getName(), 
					item.getProduct().getPrice(),
					item.getQuantity(),
					item.calculateItemTotal());
		}
		
		formatter.format("| ---------------------------------------------------------------------------------------------- |%n");
		formatter.format("%n");
		
		formatter.format("Total: %.2f", calculateTotal());
		
		String result = formatter.toString();
		formatter.close();
		
		return result;
	}
}
