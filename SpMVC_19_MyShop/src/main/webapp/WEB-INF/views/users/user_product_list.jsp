<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/include/context-menu.jsp" %>

<script>
$(function(){
	
	$(".prolist_tr").click(function(){
		//let plist = $(this).children();
		let id = $(this).data("id")
		document.location.href="${rootPath}/user/product/detail/"+id
	})
	
})
</script>

<table class="table">

	<tr class="table-primary">
		<th>상품이름</th>
		<th>희망소비자가격</th>
	</tr>

<c:choose>
	<c:when test="${empty PRO_LIST}">
		<tr>
			<td colspan="6">상품리스트가 없읍니다</td>
		</tr>
	</c:when>
	
	<c:otherwise>
	<c:forEach var="LIST" items="${PRO_LIST}" varStatus="i">
	<tr class="prolist_tr table-info" data-id="${LIST.id}">
		<td>${LIST.p_name}</td>
		<td>${LIST.p_oprice}</td>
	</tr>
	</c:forEach>
	</c:otherwise>

</c:choose>

</table>