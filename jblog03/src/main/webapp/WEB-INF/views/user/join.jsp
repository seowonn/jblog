<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<!-- modelAttribute를 get, post 다 넘겨줌으로써 userVo를 객체화해 전달한다. -->
		<form:form
			modelAttribute="userVo"
			class="join-form"
			id="join-form"
			method="post"
			action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name"><spring:message code="user.join.label.name" /></label>
			<form:input path="name" />
			<p style="color:#f00; text-align:left; padding:0">
				<form:errors path="name" />
			</p>
			
			<input type="submit" value="가입하기">
		</form:form>
		

	</div>
</body>
</html>
