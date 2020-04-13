<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
</head>

<script>
$(function(){
	$(document).on("click","#btn-score",function(){
		document.location.href="${rootPath}/score"
	})
	
	$(document).on("click","#btn-student",function(){
		document.location.href="${rootPath}/student"
	})
})
</script>

<body>

<div class="container">
	<h2>대한 고등학교 성적 관리 시스템</h2>
	<div>
	<button class="btn btn-info" type="button" id="btn-score">성적표 관리</button>
	<button class="btn btn-success" type="button" id="btn-student">학생부 관리</button>
	</div>
	
</div>


</body>
</html>