<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<style>
section {
margin: 10px auto;
width:50%;
}

form label{
display: inline-block;
width: 150px;
text-align: right;
}

.auth {
margin: 5px;
}

#auth_append{
margin: 5px;
margin-right: 15px;
}

#btn_save{
margin: 10px;
}

#auth_box,#btn_save{
}

form input.auth {
	/* display: block; */
}
</style>

	<section class="container">
		<form:form modelAttribute="userVO" class="list-group">

			<div class="list-group-item list-group-item-primary">
				<label for="username">UserName : </label>
				<form:input path="username" readonly="true" />
			</div>
			<div class="list-group-item list-group-item-info">
			<label for="email">Email : </label>
				<form:input path="email" />
			</div>
			<div class="list-group-item list-group-item-info">
			<label for="phone">Phone : </label>
				<form:input path="phone" />
			</div>
			<div class="list-group-item list-group-item-info">
			<label for="address">Address : </label>
				<form:input path="address" />
			</div>
			<div class="list-group-item list-group-item-info">
			<label for="address">계정 활성화 : </label>
				<form:checkbox path="enabled" />
			</div>

			<div id="auth_box"  class="list-group-item list-group-item-info">
				<button id="auth_append" type="button" class="btn btn-danger">권한 정보 입력 추가</button>
				<c:if test="${not empty userVO.authorities}">
					<c:forEach items="${userVO.authorities}" var="auth">
						<input name="auth" value="${auth.authority}" class="auth">
					</c:forEach>
				</c:if>
			</div>

			<div>
				<button type="button" id="btn_save" class="btn btn-success">저장</button>
			</div>

		</form:form>
	</section>







