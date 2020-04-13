<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf"%>
<%@ include file="/WEB-INF/views/include/include-body.jspf"%>
</head>
<script>
$(function(){
	$(document).on("click","#btn-home",function(){
		document.location.href="${rootPath}/"
	})
	
	$(document).on("click","#btn-insert",function(){
		document.location.href="${rootPath}/score/insert"
	})
	
	$(document).on("click",".sList",function(){
		let s_num = $(this).attr("data-id")
		document.location.href="${rootPath}/score/update?s_num=" + s_num
	})
	
})
</script>
<body>

	<div class="container">
		<h2>대한 고등학교 성적 관리 시스템</h2>

		<div>
			<table class="table table-hover">
				<tr>
					<th>학번</th>
					<th>국어</th>
					<th>영어</th>
					<th>수학</th>
					<th>과학</th>
					<th>국사</th>
				</tr>
				<c:forEach items="${SCORE_LIST}" var="SCORE" varStatus="i">
				<tr class="sList" data-id="${SCORE.s_num}">
					<td>${SCORE.s_num}</td>
					<td>${SCORE.s_kor}</td>
					<td>${SCORE.s_eng}</td>
					<td>${SCORE.s_math}</td>
					<td>${SCORE.s_si}</td>
					<td>${SCORE.s_his}</td>
				</tr>
				</c:forEach>
			</table>
			
			<button class="btn btn-primary" type="button" id="btn-home">홈으로</button>
			<button class="btn btn-success" type="button" id="btn-insert">성적입력</button>
			
		</div>
	</div>

</body>
</html>