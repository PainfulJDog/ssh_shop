package com.wzf.order;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wzf.cart.Cart;
import com.wzf.cart.CartItem;
import com.wzf.user.User;
import com.wzf.utils.Page;
import com.wzf.utils.PaymentUtil;

public class OrderAction extends ActionSupport{
	private Order order;
	private int oid;
	private OrderService orderService;
	private String pd_FrpId;
	//����ɹ�����Ҫ�Ĳ���
	private String p1_MerId;
	private String r0_Cmd;
	private String r1_Code;
	private String r2_TrxId;
	private String r3_Amt;
	private String r4_Cur;
	private String r5_Pid;
	private String r6_Order;
	private String r7_Uid;
	private String r8_MP;
	private String r9_BType;
	private String ru_Trxtime;
	private String ro_BankOrderId;
	private String rb_BankId;
	private String rp_PayDate;
	private String rq_CardNo;
	private String rq_SourceFee;
	private String rq_TargetFee;
	private String hmac;
	//��̨��ѯ��Ҫʹ�õ�����
	private int currentPage;
	private int state;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setP1_MerId(String p1_MerId) {
		this.p1_MerId = p1_MerId;
	}

	public void setR0_Cmd(String r0_Cmd) {
		this.r0_Cmd = r0_Cmd;
	}

	public void setR1_Code(String r1_Code) {
		this.r1_Code = r1_Code;
	}

	public void setR2_TrxId(String r2_TrxId) {
		this.r2_TrxId = r2_TrxId;
	}

	public void setR4_Cur(String r4_Cur) {
		this.r4_Cur = r4_Cur;
	}

	public void setR5_Pid(String r5_Pid) {
		this.r5_Pid = r5_Pid;
	}

	public void setR7_Uid(String r7_Uid) {
		this.r7_Uid = r7_Uid;
	}

	public void setR8_MP(String r8_MP) {
		this.r8_MP = r8_MP;
	}

	public void setR9_BType(String r9_BType) {
		this.r9_BType = r9_BType;
	}

	public void setRu_Trxtime(String ru_Trxtime) {
		this.ru_Trxtime = ru_Trxtime;
	}

	public void setRo_BankOrderId(String ro_BankOrderId) {
		this.ro_BankOrderId = ro_BankOrderId;
	}

	public void setRb_BankId(String rb_BankId) {
		this.rb_BankId = rb_BankId;
	}

	public void setRp_PayDate(String rp_PayDate) {
		this.rp_PayDate = rp_PayDate;
	}

	public void setRq_CardNo(String rq_CardNo) {
		this.rq_CardNo = rq_CardNo;
	}

	public void setRq_SourceFee(String rq_SourceFee) {
		this.rq_SourceFee = rq_SourceFee;
	}

	public void setRq_TargetFee(String rq_TargetFee) {
		this.rq_TargetFee = rq_TargetFee;
	}

	public void setHmac(String hmac) {
		this.hmac = hmac;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * ��������
	 */
	public String createOrder(){
		order=new Order();
		order.setOrdertime(new Date());
		order.setState(1);//1δ���2�Ѹ��3�ѷ�����4���ջ�
		HttpServletRequest request=ServletActionContext.getRequest();
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			this.addActionMessage("����û�й�����ȹ��");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		User userFromDB=(User) request.getSession().getAttribute("userFromDB");
		if(userFromDB==null){
			this.addActionMessage("����û�е�¼�����ȵ�¼��");
			return "msg";
		}
		order.setUser(userFromDB);
		for (CartItem cartItem : cart.getCartItemsFromMap()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//��չ��ﳵ
		cart.clearCart();
		int oid=orderService.save(order);
		order=orderService.findByOid(oid);
//		for (OrderItem orderItem : order.getOrderItems()) {
//			System.out.println(orderItem.getProduct().getImage());
//		}
		return "cteateOrderSuccess";
	}
	
	/**
	 * ��������
	 * @throws IOException 
	 */
	public String payOrder() throws IOException{
		//�޸Ķ�������ַ���������绰��
		//��ѯĳid�Ķ���
		Order currentOrder=orderService.findByOid(order.getOid());
		currentOrder.setAddr(order.getAddr());
		currentOrder.setName(order.getName());
		currentOrder.setPhone(order.getPhone());
		
		orderService.update(currentOrder);
		//����
		//���帶�����
		String p0_Cmd="Buy";									//ҵ������
		String p1_MerId="10001126856";					//�̻����
		String p2_Order="xxWebMart"+order.getOid().toString();		//�̻�������
		String p3_Amt="0.01";									//֧�����
		String p4_Cur="CNY";									//���ױ���
		String p5_Pid="";											//��Ʒ����
		String p6_Pcat="";											//��Ʒ����
		String p7_Pdesc="";										//��Ʒ����
		String p8_Url="http://192.168.10.101:8080/ssh_shop/order_callBack.action";//�̻�����֧���ɹ����ݵĵ�ַ
		String p9_SAF="";											//�ͻ���ַ
		String pa_MP="";											//�̻���չ��Ϣ
		String pr_NeedResponse="1";	//Ӧ�����
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		StringBuffer sb=new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.sendRedirect(sb.toString());
		return NONE;
	}
	
	/**
	 * ����ɹ���Ļص�����
	 */
	public String callBack(){
		Order currentOrder=orderService.findByOid(Integer.parseInt(r6_Order.substring(10)));
		currentOrder.setState(2);
		orderService.update(currentOrder);
		
		this.addActionMessage("����ɹ��������ţ�"+r6_Order+"�������"+r3_Amt+"��");
		return "msg";
	}
	/**
	 * @return
	 * ���û�id��ѯ����
	 */
	public String findByUid(){
		//����û�����
		User userFromDB=(User) ServletActionContext.getRequest().getSession().getAttribute("userFromDB");
		List<Order>orderList=orderService.findByUid(userFromDB);
		//ѹջ
		ActionContext.getContext().getValueStack().set("orderList", orderList);
		return "findByUidSuccess";
	}
	/**
	 * ��ѯ����
	 */
	public String findByOid(){
		order=orderService.findByOid(oid);
		return "findByOidSuccess";
	}
	/**
	 * ��̨��״̬��ѯ����
	 */
	public String adminFindByState(){
		Page<Order> page=orderService.findByPage(currentPage,state);
		ActionContext.getContext().getValueStack().set("page", page);
		return "adminFindByStateSuccess";
	}
	/**
	 * ��̨��ѯ���ж���
	 */
	public String adminFindAll(){
		Page<Order> page=orderService.findByPage(currentPage);
		ActionContext.getContext().getValueStack().set("page", page);
		return "adminFindAllSuccess";
	}
	/**
	 * ��̨�޸Ķ���״̬
	 */
	public String adminUpdateState(){
		order=orderService.findByOid(oid);
		order.setState(3);
		orderService.update(order);
		return "adminUpdateStateSuccess";
	}
	public String updateState(){
		order=orderService.findByOid(oid);
		order.setState(4);
		orderService.update(order);
		return "updateStateSuccess";
	}
}
