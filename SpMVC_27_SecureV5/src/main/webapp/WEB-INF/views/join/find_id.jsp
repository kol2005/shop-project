<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf"%>
<style>
section.email_body {
	width: 80%;
	margin: 120px auto;
	display: flex;
	flex-flow: column;
	justify-content: center;
	align-items: center;
}

span#secret {
	display: none;
}

.input-secret-code.secret-code-button {
	margin-top: 20px;
}
</style>
<script>
	$(function() {

		$(document).on("click", "#btn_email_ok", function() {
			let secret_key = $("span#secret").text()
			let secret_value = $("input#email_ok").val()
			if (secret_value == "") {
				alert("인증코드를 입력한 후 인증버튼을 클릭하세요")
				$("input#email_ok").focus()
				return false
			}
			//alert($("#email").val())
			$.ajax({
				url : "${rootPath}/join/findid_email_token_check",
				method : "POST",
				data : {
					"${_csrf.parameterName}" : "${_csrf.token}",
					email : $("#email").val(),
					secret_key : secret_key,
					secret_value : secret_value
				},
				success : function(result) {
					//email : $("#email").val()
					alert(result)
					document.location.href = "${rootPath}/join/re_join"
				},
				error : function() {
					alert("서버 통신 오류")
				}

			})// ajax

		})// click,btn_email_ok

	})
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>
	<section class="email_body container">
		<h2>Email 로 ID 찾기</h2>
		<div>Email을 인증해서 ID를 찾으세요</div>
		<p>
			<form:form action="${rootPath}/join/find_userid"
				modelAttribute="userVO">
				<div class="input-group mb-3">
					<form:input type="email" path="email" placeholder="이메일을 입력하세요"
						class="form-control" />
					<div class="input-group-append">
						<span class="input-group-text">@example.com</span>
					</div>
				</div>
				<c:choose>
					<c:when test="${JOIN == 'EMAIL_OK'}">
						<button class="btn btn-primary">Email 인증 다시 보내기</button>
						<p>E-mail을 열어서 인증코드를 확인한 후 아래 입력란에 입력 후 인증 버튼을 클릭하세요</p>
						<div class="input-secret-code">
							<span id="secret">${My_Email_Secret}</span> <input id="email_ok"
								class="form-control" placeholder="인증코드를 입력하세요">
								<br/>
							<button type="button" id="btn_email_ok"
								class="btn btn-primary secret-code-button">인증하기</button>
						</div>
					</c:when>
					<c:otherwise>
						<button class="btn btn-primary">Email 인증 보내기</button>
					</c:otherwise>
				</c:choose>
			</form:form>
		</p>
	</section>


</body>
</html>