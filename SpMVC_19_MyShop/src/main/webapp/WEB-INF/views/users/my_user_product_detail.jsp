<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="/WEB-INF/views/include/context-menu.jsp" %>
<style>
h2{
text-align: center;
}
.u_name{
color: red;
font-size: 70px;
padding: 20px;
}
.u_price{
color: blue;
font-size: 70px;
padding: 20px;
}
.u_text{
text-align: center;
margin: 30px;
padding: 10px;
border: 1px solid blue;
width: 50%;
height: 60%;
}
.p-2{
border-bottom: 1px solid black;
}
.main-box{
width: 100%;
}
.btn-{
padding: 10px;
margin: 20px;
}
.dropdown-toggle{
width: 50%;
}
</style>
<div class="container mt-3">
	
	<div class="d-inline-flex main-box">
		<div class="u_view"><div>${proUserVO.p_detail}</div></div>
			<div class="d-inline-flex-row u_text justify-content-center">
				<div class="p-2 u_name">${proUserVO.p_name}</div>
				<div class="p-2 u_price">${proUserVO.p_oprice}원</div>
				
				<div class="p-2 dropdown">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">쿠폰</button>
					<div class="dropdown-menu">
						<h5 class="dropdown-header">헤드</h5>
						<a class="dropdown-item" href="#">링크1</a>
						<a class="dropdown-item" href="#">링크2</a>
						<a class="dropdown-item" href="#">링크3</a>
						<a class="dropdown-item" href="#">링크4</a>
					</div>
				</div>
				
				<div class="p-2 dropdown">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">카드할인</button>
					<div class="dropdown-menu">
						<h5 class="dropdown-header">헤드</h5>
						<a class="dropdown-item" href="#">링크1</a>
						<a class="dropdown-item" href="#">링크2</a>
						<a class="dropdown-item" href="#">링크3</a>
						<a class="dropdown-item" href="#">링크4</a>
					</div>
				</div>
				
				<button class="btn btn-primary btn-">장바구니 담기</button>
				<button class="btn btn-success btn-">바로구매</button>
			</div>
	</div>

	<div class="d-inline-flex align-self-center">
		
	</div>
	



</div>

