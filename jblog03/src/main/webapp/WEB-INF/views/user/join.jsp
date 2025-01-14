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
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function() {
	$("#btn-checkemail").click(function() {
		var id = $("#id").val();
		if(id == "") {
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath}/api/user/checkId?id=" + id,
			type: "get",
			dataType: "json",
			success: function(response){
				if(response.result != "success") {
					console.error(response.message);
					return;
				}
				
				if(response.data.exist) {
					alert("이메일이 존재합니다. 다른 이메일을 사용해 주세요.");
					$("#id").val("");
					$("#id").focus();
					return;
				}
				
				$("#img-check").show();
				$("#btn-checkemail").hide();
			},
			error: function(xhr, status, err) {
				console.error(err);
			}
		})
	})
})
</script>
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
			<form:input path="name" type="text" />
			<p style="color:#f00; text-align:left; padding:0">
				<form:errors path="name" />
			</p>
			
			<spring:message code="user.join.label.id.check" var="userJoinLabelIdCheck" />
			<label class="block-label" for="blog-id"><spring:message code="user.join.label.id" /></label>
			<form:input path="id" type="text" />
			<input id="btn-checkemail" type="button" value="id ${userJoinLabelIdCheck }" style="display;" />
			<img id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png" style="vertical-align: bottom; width:24px; display: none" />
			<p style="color:#f00; text-align:left; padding:0">
				<form:errors path="id" />
			</p>
			
			<label class="block-label" for="password"><spring:message code="user.join.label.password" /></label>
			<form:input path="password" type="password" />
			<p style="color:#f00; text-align:left; padding:0">
				<form:errors path="password" />
			</p>
			
			<fieldset>
				<legend><spring:message code="user.join.label.terms" /></legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y" />
				<label><spring:message code="user.join.label.terms.message" /></label>
			</fieldset>
			
			<spring:message code="user.join.button.signup" var="userJoinButtonSignup"/>
			<input type="submit" value="${userJoinButtonSignup }">
		</form:form>
		

	</div>
</body>
</html>
