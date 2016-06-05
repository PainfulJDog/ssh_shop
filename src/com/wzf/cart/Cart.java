package com.wzf.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Integer,CartItem> map=new HashMap<Integer,CartItem>();
	private Double total=0d;
	private Collection<CartItem> cartItemsFromMap;
	public Collection<CartItem> getCartItemsFromMap(){
		return map.values();
	}
	
	//将购物项添加到购物车
	public void addToCart(CartItem cartItem){
		int pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//购物车中已经有这个商品了
			CartItem cartItemInCart=map.get(pid);
			cartItemInCart.setCount(cartItem.getCount()+cartItemInCart.getCount());
		}else{
			//购物车中不存在有这个商品
			map.put(pid, cartItem);
		}
		total=total+cartItem.getSubtotal();
	}
	//移除
	public void removeFromCart(Integer pid){
		//从map中移除
		CartItem cartItem =map.remove(pid);
		//总计中减去
		total=total-cartItem.getSubtotal();
	}
	//清空购物车
	public void clearCart(){
		//清空Map
		map.clear();
		//总计设为0
		total=0d;
	}
	
	
	public Map<Integer, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}
	public Double getTotal() {
		return total;
	}
	
}
