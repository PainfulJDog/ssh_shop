package com.wzf.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wzf.product.Product;
import com.wzf.product.ProductService;

public class CartAction extends ActionSupport {
	//����pid
	private int pid;
	//����count
	private int count;
	private ProductService productService;
	
	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * ��session�л�ȡ���ﳵ
	 */
	public Cart getCart(HttpServletRequest request){
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	/**
	 * ��ӵ����ﳵ
	 * @return
	 */
	public String addToCart(){
		//����pid��product
		Product product=productService.findByPid(pid);
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);
		
		Cart cart=this.getCart(ServletActionContext.getRequest());
		cart.addToCart(cartItem);
		return "addToCartSuccess";
	}
	/**
	 * ��չ��ﳵ
	 */
	public String clearCart(){
		Cart cart=this.getCart(ServletActionContext.getRequest());
		cart.clearCart();
		return "clearCartSuccess";
	}
	public String removeFromCart(){
		Cart cart=this.getCart(ServletActionContext.getRequest());
		cart.removeFromCart(pid);
		return "removeFromCartSuccess";
	}
	public String accessCart(){
		return "accessCartSuccess";
	}
}
