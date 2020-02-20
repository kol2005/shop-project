<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>나의 홈페이지</title>
</head>
<body>
<h2>학생점수</h2>
<form action="/app/number/score_input" method=POST>
	<input name="num1" placeholder="국어점수" value="${scoreVO.kor}"><br>
	<input name="num2" placeholder="영어점수" value="${scoreVO.eng}"><br>
	<input name="num3" placeholder="수학점수" value="${scoreVO.math}"><br>
	<input name="num4" placeholder="과학점수" value="${scoreVO.sci}"><br>
	<input name="num5" placeholder="음악점수" value="${scoreVO.music}"><br>
	<button>계산</button>
</form>
<div><b>총점 : </b>${scoreVO.sum}</div>
<div><b>평균 : </b>${scoreVO.avg}</div>
</body>
</html>