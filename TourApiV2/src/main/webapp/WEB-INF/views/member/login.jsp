<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jsp" %>
</head>
<style>
input{
margin-top:2vh;
margin-bottom: 2vh;
}
button{
margin-left: 1vw;
margin-right:1vw;
margin-top:2vh;
margin-bottom: 2vh;
}
</style>
<script type="text/javascript">
$(function() {
	$(document).on("click","button.join",function(){
		document.location.href="${rootPath}/join"
	})
})
</script>
<body>
<form:form action="${rootPath }/login" method="post" class="form-group container">
<h2 class="jumbotron">로그인</h2>

<div>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
<span>${SPRING_SECURITY_LAST_EXCEPTION.message}</span>
</c:if>
</div>

<input id="username" name="username" placeholder="User ID" class="form-control">
<input type="password" id="password" name="password" placeholder="password" class="form-control">
<button class="btn btn-outline-primary">login</button>
<button class="btn btn-outline-success join" type="button">회원가입</button>
</form:form>
</body>
</html>