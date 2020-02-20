<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="/WEB-INF/views/include/context-menu.jsp" %>
<style>
.u_name{
color: red;
font-size: 100px;
padding: 20px;
text-align: left;
}
.u_price{
color: blue;
font-size: 70px;
padding: 20px;
text-align: right;
justify-content: right;
}
</style>
<div>
	<div class="">
		<a class="u_name">${proUserVO.p_name}</a>
		<a class="u_price">${proUserVO.p_oprice}원</a>
		<a class="u_view">${proUserVO.p_detail}</a>
	</div>
	<div>
		<button>장바구니 담기</button>
	</div>
</div>

