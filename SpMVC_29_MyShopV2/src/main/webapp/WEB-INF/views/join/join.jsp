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
$(function(){
	
	$(document).on("click","#btn-loss",function(){
		document.location.href = "${rootPath}/join/find_userid"
	})
	
	$(document).on("click","#btn-join",function(){
		
		// 유효성검사
		// id,password가 입력되지 않았을때 경고
		let username = $("#username")
		let password = $("#password")
		let re_password = $("#re_password")
		
		if(username.val() == "") {
			alert("아이디를 입력하세요")
			username.focus()
			return false;
		}
		
		if(password.val() == "") {
			alert("비밀번호를 입력하세요")
			password.focus()
			return false;
		}
		
		if(re_password.val() == "") {
			alert("비밀번호를 확인하세요")
			re_password.focus()
			return false;
		}
		
		if(password.val() != re_password.val()){
			alert("비밀번호와 비밀번호 확인이 다릅니다")
			password.focus()
			return false;
		}
		$("form").submit()
		
	})
	
	// 현재 입력박스에서 포커스가 벗어났을때 발생하는 이벤트
	$(document).on("blur","#username",function(){
		let username = $(this).val()
		if(username == ""){
			$("#m_username").text("아이디는 반드시 입력해야 합니다")
			$("#username").focus()
			return false;
		}
		
		$.ajax({
			url : "${rootPath}/user/idcheck",
			method : "GET",
			data : {username : username},
			success : function(result){
				console.log(result)
				if(result == "EXISTS"){
					$("#m_username").text("이미 가입된 사용자 이름입니다")
					$("#m_username").css("color","red")
					$("#username").focus()
					return false
				}else {
					$("#m_username").text("* 사용가능한 ID 입니다")
				}
			},
			error:function(){
				$("#m_username").text("* 서버통신오류")
				return false
			}
		})
	})
	
	// 현재 DOM 화면에 class가 view_pass인 모든것에 적용 
	$(".view_pass").each(function(index,input){
		// 매개변수로 전달된 input을 선택하여
		// 변수 $input에 임시 저장하라
		let input_ref = $(input)
		$("input#view_pass").click(function(){
			let change = $(this).is(":checked") ? "text" : "password";
			// 가상의 input 생성
			// <input type='text' class='view_pass form-control' id='password' name='password'>
			// 또는 <input type='password' class='view_pass form-control' id='password' name='password'>
			let ref = $("<input type='" + change + "' class='view_pass form-control' id='password' name='password'/>")
			.val(input_ref.val())
			.insertBefore(input_ref);
			
			input_ref.remove();
			input_ref = ref;
		})
	})
	
		// 현재 DOM 화면에 class가 re_view_pass인 모든것에 적용 
	$(".re_view_pass").each(function(index,input){
		// 매개변수로 전달된 input을 선택하여
		// 변수 $input에 임시 저장하라
		let input_ref = $(input)
		$("input#view_pass").click(function(){
			let change = $(this).is(":checked") ? "text" : "password";
			// 가상의 input 생성
			// <input type='text' class='re_view_pass form-control' id='re_password' name='re_password'>
			// 또는 <input type='password' class='re_view_pass form-control' id='re_password' name='re_password'>
			let ref = $("<input type='" + change + "' class='re_view_pass form-control' id='re_password' name='re_password' />")
			.val(input_ref.val())
			.insertBefore(input_ref);
			
			input_ref.remove();
			input_ref = ref;
		})
	})
	
	
})
</script>

</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>
<div class="container container-fluid">
	<form:form method="POST" action="${rootPath}/join/joinok" class="join_form" modelAttribute="userVO">
		<h2>회원가입</h2>
		
		<div class="input-tag container-fluid">
			<form:input type="text" path="username" 
			placeholder="User ID" class="form-control input-username"/>
			<div class="message" id="m_username"></div>
			
			<form:input type="password" class="view_pass form-control"
			 path="password" placeholder="비밀번호"/><br>
			<input type="password" class="re_view_pass form-control"
			 id="re_password" name="re_password" placeholder="비밀번호">
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