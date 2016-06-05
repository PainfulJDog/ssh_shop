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
	
	//����������ӵ����ﳵ
	public void addToCart(CartItem cartItem){
		int pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//���ﳵ���Ѿ��������Ʒ��
			CartItem cartItemInCart=map.get(pid);
			cartItemInCart.setCount(cartItem.getCount()+cartItemInCart.getCount());
		}else{
			//���ﳵ�в������������Ʒ
			map.put(pid, cartItem);
		}
		total=total+cartItem.getSubtotal();
	}
	//�Ƴ�
	public void removeFromCart(Integer pid){
		//��map���Ƴ�
		CartItem cartItem =map.remove(pid);
		//�ܼ��м�ȥ
		total=total-cartItem.getSubtotal();
	}
	//��չ��ﳵ
	public void clearCart(){
		//���Map
		map.clear();
		//�ܼ���Ϊ0
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
