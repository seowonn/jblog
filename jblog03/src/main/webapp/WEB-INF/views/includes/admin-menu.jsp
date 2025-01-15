<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<ul class="admin-menu">
    <li class="<c:if test='${menu == "basic"}'>selected</c:if>">
        <a href="${pageContext.request.contextPath}/jblog/${authUser.id}/admin/basic">기본설정</a>
    </li>
    <li class="<c:if test='${menu == "category"}'>selected</c:if>">
        <a href="${pageContext.request.contextPath}/jblog/${authUser.id}/admin/category">카테고리</a>
    </li>
    <li class="<c:if test='${menu == "write"}'>selected</c:if>">
        <a href="${pageContext.request.contextPath}/jblog/${authUser.id}/admin/write">글작성</a>
    </li>
</ul>

