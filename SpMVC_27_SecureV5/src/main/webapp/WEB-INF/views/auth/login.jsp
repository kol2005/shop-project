<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<style>
*{
width: 70%;
margin: 0 auto;
}

body{
box-sizing: border-box;

}

.container{
padding: 50px;
text-align: center;
margin: 200px;
border: 1px solid red;
}

.btn{
margin: 10px;
}
.error-message{
color: red;
font-weight: bold;
}
</style>


<script>
$(function(){
	$(document).on("click","button.join",function(){
		document.location.href = "${rootPath}/join"
	})
})
</script>
</head>
<body>
		<div class="container">
	<form:form action="${rootPath}/login" method="POST">
		<h2>로그인</h2>
		
		<div>
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				<span class="error-message">${SPRING_SECURITY_LAST_EXCEPTION.message}</span>
			</c:if>
		</div>
		
		<div class="form-group">
		<label for="uname">Username:</label>
		<input id="username" name="username" placeholder="User ID" class="form-control">
		</div>
		<div class="form-group">
		<label for="pwd">Password:</label>
		<input id="password" name="password" type="password" placeholder="비밀번호" class="form-control">
		</div>
		<button class="btn btn-success">로그인</button>
		<button class="btn btn-info join" type="button">회원가입</button>
	</form:form>
	</div>
</body>
</html>