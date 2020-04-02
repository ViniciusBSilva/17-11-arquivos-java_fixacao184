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

	public String toString () {
		Formatter formatter = new Formatter();
		
		//formatter.format(format, args);
		
		String result = formatter.toString();
		formatter.close();
		
		return result;
	}
}
