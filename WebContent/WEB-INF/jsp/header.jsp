<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/index.action">
				<img src="${pageContext.request.contextPath}/resources/image/r___________renleipic_01/logo.gif" alt="传智播客"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/resources/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.userFromDB!=null">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<s:property value="#session.userFromDB.name"/>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/user_logout.action">注销</a>|
					</li>
				</s:if>
				<s:else>
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/user_loginPage.action">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath}/user_registerPage.action">注册</a>|
					</li>
				</s:else>
				<li id="headerUsername" class="headerUsername"></li>
				<li id="headerLogout" class="headerLogout">
					<a>[退出]</a>|
				</li>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="./购物车.htm">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${pageContext.request.contextPath}/index.action">首页</a>
						|
					</li>
					<s:iterator var="c" value="#session.categoryList">
						<li>
							<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=1"><s:property value="cname"/></a>
							|
						</li>
					</s:iterator>
					
		</ul>
	</div>

<div class="span24">
		<div class="tagWrap">
			<ul class="tag">
						<li class="icon" style="background: url(http://storage.shopxx.net/demo-image/3.0/tag/hot.gif) right no-repeat;">
							<a >热销</a>
						</li>
						<li class="icon" style="background: url(http://storage.shopxx.net/demo-image/3.0/tag/new.gif) right no-repeat;">
							<a>最新</a>
						</li>
			</ul>
			<div class="hotSearch">
					热门搜索:
						<a >水蜜桃</a>
						<a>西瓜</a>
						<a>紫薯</a>
						<a>大米</a>
						<a>玉米</a>
						<a>茄子</a>
						<a>辣椒</a>
						<a>圣女果</a>
						<a>鱿鱼丝</a>
			</div>
			<div class="search">
				<form id="productSearchForm" method="get">
					<input name="keyword" class="keyword" value="商品搜索" maxlength="30">
					<button type="submit">搜索</button>
				</form>
			</div>
		</div>
	</div>
</div>	