<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp"/>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="startNumber" value="${data.size() }" /> <!-- 이거 조회된 총 카테고리 수 -->
					<c:forEach items="${data }" var="dto" varStatus="status" >
						<tr>
							<td>${startNumber-status.index }</td>
							<td>${dto.name }</td>
							<td>${dto.count }</td>
							<td>${dto.description }</td>
							<td>
							    <a href="${pageContext.request.contextPath}/jblog/${authUser.id}/admin/category/delete?category-id=${dto.id}">
							        <img src="${pageContext.request.contextPath}/assets/images/delete.jpg" alt="삭제">
							    </a>
							</td>
						</tr>
					</c:forEach>			  
				</table>
      	
      			<form class="category-form" method="post" 
      					action="${pageContext.request.contextPath}/jblog/${authUser.id }/admin/category/add">
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<!-- ~id랑 지금 명이 겹쳐서 같이 인식돼서 임의로 0 넣고 있음.. -->
			      		<tr>
				            <td colspan="2">
				                <input type="hidden" name="id" value="0">
				            </td>
				        </tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
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