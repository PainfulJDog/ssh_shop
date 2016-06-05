<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>XX网上商城--我的订单</title>
<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/resources/css/cart.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current">/li>
					<li  >订单列表</li>
				</ul>
			</div>
	
		
				<table>
					<tbody>
					<s:iterator var="order" value="orderList">
					<tr>
						<th colspan="6">
							订单号：<s:property value="#order.oid"/> 
							金额：<s:property value="#order.total"/>
							状态：<s:if test="#order.state==1"><a href="${pageContext.request.contextPath}/order_findByOid?oid=<s:property value="#order.oid"/> "><font color="red">待付款，点击去付款</></a></s:if>
									<s:elseif test="#order.state==2"><font color="green">已付款</font></s:elseif>
									<s:elseif test="#order.state==3"><a href="#">确认收货</a></s:elseif>
									<s:elseif test="#order.state==4">已收货，订单完成</s:elseif>
							</th>
					</tr>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
						<s:iterator var="orderItem" value="#order.orderItems">
							<tr>
								<td width="60">
									<img src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>"/>
								</td>
								<td>
									<a target="_blank"><s:property value="#orderItem.product.pname"/></a>
								</td>
								<td>
									<s:property value="#orderItem.product.shop_price"/>
								</td>
								<td class="quantity" width="60">
									<s:property value="#orderItem.count"/>
								</td>
								<td width="140">
									<span class="subtotal">￥<s:property value="#orderItem.subtotal"/></span>
								</td>
								<td>
									<a href="./cart_removeCart.action?pid=1" class="delete">删除</a>
								</td>
							</tr>
							</s:iterator>
								<tr>
									<td colspan="6">&nbsp;
									</td>
								</tr>
						</s:iterator>
				</tbody>
			</table>
		</div>
		
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/resources/image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body>
</html>