<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<%@ include file="/WEB-INF/views/include/include-header.jspf" %>

<script>
$(function(){
	$(document).on("click","#btn-home2",function(){
		document.location.href = "${rootPath}/"
	})
	
	$(document).on("click","#btn-insert2",function(){
		//document.location.href="${rootPath}/score/insert"
		
		// 유효성 검사
		let s_num = $("#s_num")
		let s_subject = $("#s_subject")
		let s_score = $("#s_score")
		
		if(s_num.val() == ""){
			alert("학번을 입력하세요")
			s_num.focus()
			return false;
		}
		if(s_subject.val() == ""){
			alert("과목을 입력하세요")
			s_subject.focus()
			return false;
		}
		if(s_score.val() == ""){
			alert("점수를 입력하세요")
			s_score.focus()
			return false;
		}
		
		
		
		$("form").submit()
	})
})
</script>
</head>
<body>

<div class="container">
<form:form method="POST" action="${rootPath}/score/insert" class="was-validated">
<h2>학생 과목 성적 입력</h2>

<div class="form-group">
<label for="num">학번 : </label>
<input id="s_num" name="s_num" placeholder="학번" value="${SCORE.s_num}">
<div class="message" id="m_num"></div>
</div>

<div class="form-group">
<label for="subject">과목 : </label>
<input id="s_subject" name="s_subject" placeholder="과목" value="${SCORE.s_subject}">
<div class="message" id="m_subject"></div>
</div>

<div class="form-group">
<label for="score">점수 : </label>
<input id="s_score" name="s_score" placeholder="점수" value="${SCORE.s_score}">
<div class="message" id="m_score"></div>
</div>

<button class="btn btn-success" type="button" id="btn-insert2">입력</button>
<button class="btn btn-primary" type="button" id="btn-home2">홈으로</button>

</form:form>
</div>

</body>
</html>