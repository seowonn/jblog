<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<form class="login-form" name="loginform" method="post" action="${pageContext.request.contextPath}/user/auth">
      		<label><spring:message code="user.signin.label.id" /></label>
      		<input type="text" name="id" value="${not empty id ? id : ''}" >
      		
      		<label><spring:message code="user.signin.label.password" /></label>
      		<input type="password" name="password">
      		
      		<c:if test='${result eq "fail"}'>
				<p>로그인이 실패 했습니다.</p>
			</c:if>
      		
      		<spring:message code="user.signin.button.text" var="userJoinButtonSignin"/>
			<input type="submit" value="${userJoinButtonSignin }">
		</form>
	</div>
</body>
</html>