<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/include-head.jsp" %>
<%@ include file="/WEB-INF/views/include/include-header.jsp %>
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
.message{
color: green;
font-weight: bold;
font-size: 0.3rem;
}
</style>
<script type="text/javascript">
$(function() {
	$(document).on("click","#btn-reset",function(){
		let username=$("#username")
		let email=$("#email")
		
		if(username.val()==""){
			alert("아이디를 입력하세요")
			username.focus()
			return false
		}
		if(email.val()==""){
			alert("이메일을 입력하세요")
			email.focus()
			return false
		}
		
		$("form").submit()
	})
	$(document).on("blur","#username",function(){//입력박스에서 포커스가 벗어났을때
		let username=$(this).val()
		if(username==""){
			$("#m_username").text("아이디는 반드시 입력해야 합니다")
			$("#m_username").focus()
			return false
		}
		$.ajax({
			url:"${rootPath}/user/idcheck",
			method:"GET",
			data:{username:username},
			success:function(result){
				if(result=="EXISTS"){
					$("#m_username").text("존재하는 사용자 이름입니다")
					$("#m_username").css("color","green")
					$("#m_username").focus()
					return false
				} else{
					$("#m_username").text("존재하지 않는 사용자 이름입니다 ")
					return false
				}
			},
			error:function(){
				$("#m_username").text("서버통신오류")
				return false
			}
		})
	})
})
</script>
<body>

<form:form modelAttribute="userVO" action="${rootPath }/password/resetPass" method="post" class="form-group container">
<h2 class="jumbotron">비밀번호 초기화 해드릴게요</h2>
<!--  name="${_csrf.parameterName }" value="${_csrf.token }">  -->
<input id="username" name="username" placeholder="User ID" class="form-control">
<p class="message" id="m_username"></p>
<input id="email" name="email" placeholder="Email" class="form-control">

<button class="btn btn-outline-success" id="btn-reset" type="button">비번을 초기화 해주세요</button>
</form:form>
</body>
</html>