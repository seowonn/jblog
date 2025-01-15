<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
	<c:choose>
		<c:when test="${empty authUser }">
			<h1>Spring 이야기</h1>
		</c:when>
		<c:otherwise>
			<h1>${blogVo.title }</h1>
		</c:otherwise>
	</c:choose>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath }/jblog/${blogVo.blogId }/admin/basic">블로그 관리</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>