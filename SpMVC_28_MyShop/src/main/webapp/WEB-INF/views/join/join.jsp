<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<style>
*{


}

body{

box-sizing: border-box;

}

.container{
margin: 10rem auto;
width: 50%;
padding: 50px;
text-align: center;
border: 1px solid red;

}

.input-username{
margin-left: 10px;
}

.btn{
margin: 10px;
}

.message{
color: green;
font-weight: bold;
font-size: 0.3rem;

}

.view_pass{
margin-top: 10px;
margin-left: 10px;
}
.re_view_pass{
margin-left: 10px;
}

</style>
<script>

</script>

</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>
<div class="container container-fluid">
	<form:form method="POST" action="${rootPath}/join/joinok" class="join_form" modelAttribute="userVO">
		<h2>회원가입</h2>
		<div class="input-tag container-fluid">
			<form:input type="text" path="username" placeholder="User ID" class="form-control input-username"/>
			<div class="message" id="m_username"></div>
			
			<form:input type="password" class="view_pass form-control" path="password" placeholder="비밀번호"/><br>
			<input type="password" class="re_view_pass form-control" id="re_password" name="re_password" placeholder="비밀번호">
		</div>
		<div class="option">
			<label for="view_pass">
			<input type="checkbox" id="view_pass">
			비밀번호 보이기</label>
		</div>
		
		<button class="btn btn-info" type="button" id="btn-join">회언가입</button>
		<button class="btn btn-warning" type="button" id="btn-loss">id/비밀번호 찾기</button>
	</form:form>
</div>
</body>
</html>