<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jsp"%>
<style>
body {
  font-family: "Lato", sans-serif;
}

.sidenav {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}

.sidenav a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}

h2{
text-align: center;
}

.mypage{
width: 500px;
border: 2px solid blue;
}

.my-info{
margin: 20px;
}

.my-text{
margin-left: auto;
margin-right: 0;
}

button{
margin-top: 10px;
}

</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-header.jsp"%>

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="${rootPath}/mypage/view">회원정보 보기</a>
  <a href="${rootPath}/mypage/update">회원정보 수정</a>
  <a href="${rootPath}/mypage/resetpassword">비밀번호 변경(미구현)</a>
  <a href="${rootPath}/mypage/update">포인트!@#(미구현)</a>
</div>

<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; menu open</span>

<section class="container mypage">
<h2>회원정보 수정</h2>
	<div class="my-info">
		<form:form modelAttribute="memberVO" method="POST" class="list-group">
		
			<div class="list-group-item list-group-item-primary">
			<label for="u_name">ID : </label>
			<form:input path="u_name" readonly="true" class="my-text"/>
			</div>
			
			<!-- 
			<div class="list-group-item list-group-item-primary">
			<label for="u_password">비밀번호 : </label>
			<form:input path="u_password" readonly="true" class="my-text"/>
			</div>
			 -->
			
			<div class="list-group-item list-group-item-info">
			<label for="email">E-mail : </label>
			<form:input path="email" readonly="false" class="my-text"/>
			</div>
			
			<div class="list-group-item list-group-item-info">
			<label for="phone">Phone : </label>
			<form:input path="phone" readonly="false" class="my-text"/>
			</div>
			
			<div class="list-group-item list-group-item-info">
			<label for="address">Address : </label>
			<form:input path="address" readonly="false" class="my-text"/>
			</div>
			
			<button class="btn btn-success">수정</button>
			
		</form:form>
	</div>
</section>

<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
</script>

</body>
</html>