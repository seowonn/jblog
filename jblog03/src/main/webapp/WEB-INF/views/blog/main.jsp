<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%pageContext.setAttribute("newLine", "\n");%>
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
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${empty authUser or empty post }">
							<h4>Post 기본 설정 제목</h4>
							<p>포스트를 작성해보세요.<p>
						</c:when>
						<c:otherwise>
							<c:if test="${not empty post }">							
								<h4>${post.title }</h4>
								<p>${fn:replace(post.contents, newLine, "<br>") }</p>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postVo }" var="vo">
						<li><a href="${pageContext.request.contextPath }/jblog/${authUser.id }/${vo.categoryId }/${vo.postId }">${vo.title }</a> <span>${vo.regDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }${blogVo.profile }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${data }" var="dto">
					<li><a href="${pageContext.request.contextPath }/jblog/${authUser.id }/${dto.id }">${dto.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/admin-footer.jsp"/>
	</div>
</body>
</html>