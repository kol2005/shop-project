<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jsp" %>
</head>
<style>
.serif {
	font-family: "Times New Roman", Times, serif;
}

section {
	margin-top: 5%;
	margin-bottom: 5%;
}
</style>
<style>
input {
	margin-top: 2vh;
	margin-bottom: 2vh;
}

button {
	margin-left: 1vw;
	margin-right: 1vw;
	margin-top: 2vh;
	margin-bottom: 2vh;
}

.message {
	color: green;
	font-weight: bold;
	font-size: 0.3rem;
}

form p.password {
	display: none;
}
form input.auth{
display: block;
}
</style>
<script type="text/javascript">
	$(function() {
		$("input").prop("readonly", true)
		
		$(document).on("click", "#btn-update", function() {
			let pass = $("#password").val()
			if (pass == "") {
				alert("수정하려면 비밀번호를 입력한후 다시 수정버튼을 클릭하세요")
				$("p.password").css("display", "block")
				$("#password").prop("readonly", false)
				$("#password").focus()
				return false
			}

			if (pass != "") {
				$.ajax({
					url : "${rootPath}/user/password",
					method : 'POST',
					data : {
						password : pass,
						"${_csrf.parameterName}" : "${_csrf.token}"
					},
					success : function(result) {
						if (result == 'PASS_OK') {
							$("input").prop("readonly", false)
							$("input").css("color", "blue")
							$("#btn-save").prop("disabled", false)
							$("#btn-update").prop("disabled", true)
						} else {
							alert("비밀번호가 일치하지 않습니다")
						}
					},
					error : function() {
						alert("서버통신 오류")
					}
				})
			}
		})
	})
</script>
<body>
<%@ include file="/WEB-INF/views/include/include-header.jsp %>
	<form:form modelAttribute="userVO" class="form-group container">
		<h2 class="jumbotron">회원 정보 수정</h2>
		<form:input path="username" placeholder="username" class="form-control" type="hidden"/>
		<form:input path="email" placeholder="email" class="form-control" />
		<form:input path="phone" placeholder="phone" class="form-control" />
		<form:input path="address" placeholder="address" class="form-control" />
		
		<p class="password">
			<input id="password" type="password" placeholder="비밀번호 입력"
				class="form-control">
		</p>

		<button class="btn btn-outline-primary" id="btn-update" type="button">수정</button>
		<button class="btn btn-outline-primary" id="btn-save"
			disabled="disabled">저장</button>
		<button class="btn btn-outline-primary" id="btn-loss-pass" type="button">비밀번호찾기</button>
	</form:form>
</body>
</html>