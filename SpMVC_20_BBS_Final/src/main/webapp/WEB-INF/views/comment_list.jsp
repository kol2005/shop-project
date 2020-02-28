<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
	<c:forEach items="${CMT_LIST}" var="CMT" varStatus="i">
		<div class="d-flex p2 row cmt-item" data-id="${CMT.c_id}">
			<div class="p-2">${i.count}</div>
			<div class="p-2"><b>${CMT.c_writer}</b></div>
			<div class="p-2">${CMT.c_subject}</div>
			<div class="col-1 cmt-item-del"><b>&times;</b></div>
		</div>
	</c:forEach>
