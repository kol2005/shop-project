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
</style>


<script>
$(function(){
	$(document).on("click","button.join",function(){
		document.location.href = "${rootPath}/user/join"
	})
})
</script>
</head>
<body>
		<div class="container">
	<form action="${rootPath}/user/login" method="POST">
		<h2>로그인</h2>
		<div class="form-group">
		<label for="uname">Username:</label>
		<input id="username" name="username" placeholder="User ID">
		</div>
		<div class="form-group">
		<label for="pwd">Password:</label>
		<input id="password" name="password" placeholder="비밀번호">
		</div>
		<button class="btn btn-success">로그인</button>
		<button class="btn btn-info join" type="button">회원가입</button>
	</form>
	</div>
</body>
</html>