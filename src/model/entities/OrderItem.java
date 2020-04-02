package model.entities;

import model.exceptions.DomainException;

public class OrderItem {

		private Product product;
		private Integer quantity;

		public OrderItem(String productName, Double itemValue, Integer quantity) throws DomainException {
			setInitialQuantity(quantity);
			this.product = new Product(productName, (itemValue/quantity));
		}

		public Product getProduct() {
			return product;
		}

		public Integer getQuantity() {
			return quantity;
		}

		private void setInitialQuantity(Integer quantity) throws DomainException {
			if (quantity <= 0) {
				throw new DomainException("Quantity must be greater than zero");
			}
			this.quantity = quantity;
		}
		
		public Double calculateItemTotal() {
			return product.getPrice() * quantity;
		}
		
}
