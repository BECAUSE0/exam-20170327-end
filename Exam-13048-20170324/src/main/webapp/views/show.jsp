<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">

<title>show</title>
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">13048 李婧</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>/quit"
					onclick="javascript:return confirm('确定要退出吗！')">欢迎&nbsp;&nbsp; <%=request.getSession().getAttribute("success")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a></li>
			</ul>

		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Overview <span
							class="sr-only">(current)</span></a></li>
					<br />
					<li><a href="<%=request.getContextPath()%>/views/login.jsp">登录</a></li>
					<li><a href="#">展示</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h5 class="sub-header" align="right">
					<input type="submit" value="添加"
						onclick="window.location.href='<%=request.getContextPath()%>/views/add.jsp' ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</h5>
				<h3 class="sub-header">客户列表</h3>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>操作</th>
								<th>Customer Id</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Address</th>
								<th>Email</th>
								<th>Last Update</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${list}" var="cust">
									<tr>
										<td><a
											href="<%=request.getContextPath()%>/edit?id=${cust.customerId}">编辑</a>
											|&nbsp;<a
											href="<%=request.getContextPath()%>/delete?id=${cust.customerId}"
											onclick="javascript:return confirm('确定要删除吗！')">删除</a></td>
										<td>${cust.customerId}</td>
										<td>${cust.firstName}</td>
										<td>${cust.lastName}</td>
										<td>${cust.address}</td>
										<td>${cust.email}</td>
										<td>${cust.lastUpdate}</td>
									</tr>
								</c:forEach>
							</tr>
					
						</tbody>
					</table>
					<div align="center">
						第(${page.currentPage})页/共(${total})页						
					</div>
					<div align="right">
						<a href="<%=request.getContextPath()%>/selectByPage?currentPage=1">首页</a>
						<a href="<%=request.getContextPath()%>/selectByPage?currentPage=${page.currentPage-1}">上一页</a>
						<a href="<%=request.getContextPath()%>/selectByPage?currentPage=${page.currentPage+1}">下一页</a>
						<a href="<%=request.getContextPath()%>/selectByPage?currentPage=${total}">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div><br><br><br>
				</div>
			</div>
		</div>
	</div>

	<script
		src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>

</html>