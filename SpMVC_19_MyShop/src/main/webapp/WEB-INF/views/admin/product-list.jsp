<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function(){
	
	/*
	클래스 pro_tr은 본문의 tr tag에 설정이 되어있고
	본문의 tr tag를 클릭하면 이벤트가 발생하고 함수가 실행된다
	
	이때 tr tag가 갖는 모든 정보는 this라는 내장 객체에
	담겨 함수내부로 전달된다
	*/
	/*
	tr tag가 클릭되면 id 값을 추출하고 update method로 전달하기
	*/
	$(".pro_tr").click(function(){
		
		let id = $(this).data("id")//attr("data-id")
		let c = $(this).attr("class")
		
		//document.location.href="${rootPath}/admin/product/update?id=" + id
		document.location.href="${rootPath}/admin/product/update/" + id
	})
	
})
</script>


<table class="col-md-4 col-12">
	<tr>
		<th>상품코드</th>
		<th>상품이름</th>
		<th>거래처</th>
		<th>품목</th>
		<th>매입가격</th>
		<th>판매가격</th>
	</tr>
	<c:choose>
		<c:when test="${empty PRO_LIST}">
			<tr>
				<td colspan="6">상품정보가 없습니다</td>
			</tr>
		</c:when>
	
		<c:otherwise>
			<c:forEach var="PRO" items="${PRO_LIST}" varStatus="i">
				<tr class="pro_tr" data-id="${PRO.id}">
					<td>${PRO.p_code}</td>
					<td><span  class="p_name">${PRO.p_name}</span></td>
					<td>${PRO.p_bcode}</td>
					<td>${PRO.p_dcode}</td>
					<td>${PRO.p_iprice}</td>
					<td>${PRO.p_oprice}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	

	
</table>