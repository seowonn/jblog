<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp"/>
				<form method="post" action="${pageContext.request.contextPath }/jblog/${blogVo.blogId }/admin/update" enctype="multipart/form-data">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="block-label" for="title">블로그 제목</td>
			      			<td><input id="title" type="text" size="40" name="title" value="${blogVo.title }"></td>
			      		</tr>
			      		<tr>
			      			<td class="block-label">로고이미지</td>
			      			<td><img id="profile" src="${pageContext.request.contextPath }${blogVo.profile }"></td>    			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="file" value="${blogVo.profile }"></td>      			
			      		</tr> 
			      		<tr><td><input type="hidden" name="profile" value="${blogVo.profile }"></td></tr>
			      		<tr><td><input type="hidden" name="blogId" value="${blogVo.blogId}"></td><tr/>          		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>