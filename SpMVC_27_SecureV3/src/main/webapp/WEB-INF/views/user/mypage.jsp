<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf"%>
</head>
<style>
.container {
	margin-top: 7rem;
	border: 1px solid red;
}

.btn {
	margin: 10px;
	margin-left: auto;
}
</style>
<script>
	$(function() {
		$(document).on("click", "#btn-update", function() {
			document.location.href = "${rootPath}/user/update?username=${PRINCIPAL.principal.username}"
		})
	})
</script>
<body>
	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>
	<div class="container">
		<h2>마이페이지</h2>
		<div class="list-group">
			<form:form method="POST" action="${rootPath}/user/update">
			
			<div class="list-group-item list-group-item-primary">id :
			${PRINCIPAL.principal.id}</div>
			
			
			<div class="list-group-item list-group-item-primary">아이디 :
				${PRINCIPAL.principal.username}</div>

			<div class="list-group-item list-group-item-danger">비밀번호 :
				${PRINCIPAL.principal.password}</div>

			<div class="list-group-item list-group-item-action">
				이메일 : <input id="email" class="email" value="${PRINCIPAL.principal.email}">
			</div>
			<div class="list-group-item list-group-item-action">
				전화번호 : <input id="phone" class="phone" value="${PRINCIPAL.principal.phone}">
			</div>
			<div class="list-group-item list-group-item-action">
				주소 : <input id="address" class="address" value="${PRINCIPAL.principal.address}">
			</div>
			</form:form>
		</div>
		
		<div>
			<button class="btn btn-success" id="btn-update">수정</button>
		</div>
	</div>
</body>
</html>





