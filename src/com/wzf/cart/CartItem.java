package com.wzf.cart;

import com.wzf.product.Product;

public class CartItem {
	private Product product;
	private int count;
	private Double subtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return count*product.getShop_price();
	}
	
}
