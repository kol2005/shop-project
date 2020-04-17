<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	table{
	width: 100%;
	}
	tr.tr_user{
	cursor: pointer;
	}
	th, td{
	white-space: nowrap;
	padding: 5px;
	}
	
</style>
<table class="table">
	<tr>
		<th>NO</th>
		<th class="table-primary">UserName</th>
		<th class="table-info">Email</th>
		<th class="table-info">Phone</th>
		<th class="table-info">Address</th>
	</tr>
	<c:choose>
		<c:when test="${empty userList}">
			<tr>
				<td colspan="5">User 정보 없음</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${userList}" var="user" varStatus="i">
				<tr data-id="${user.username}" class="tr_user">
					<td class="table-light">${i.count}</td>
					<td class="table-success">${user.username}</td>
					<td class="table-success">${user.email}</td>
					<td class="table-success">${user.phone}</td>
					<td class="table-success">${user.address}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>