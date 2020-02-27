<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
	<script>
	$(function(){
		
		$("button").click(function(){
			
			let txt = $(this).text()
			
			if(txt == '수정'){
				document.location.href="${rootPath}/update?b_id=${BBS.b_id}"
			}else if(txt == '삭제'){
				if(confirm("삭제할까요?")){
					document.location.replace("${rootPath}/delete/${BBS.b_id}")
				}
			}else if(txt == '저장'){
				return false
			}else {
				document.location.href="${rootPath}/list"
			}
			
		})
		
	})
	</script>
	<style>
	table.table tr:nth-child(4) th, table.table tr:nth-child(4) td {
		
		height: 400px;
	}
	table.table tr:nth-child(4) td{
		overflow-y:scroll;
		
	}
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
	<section class="container-fluid">
			<h2 class="p-3">${BBS.b_subject}</h2>
			<hr/>
			<div class="row p-3">
				<small class="col-1">${BBS.b_writer}</small>
				<small class="col-5">${BBS.b_date_time}</small>
			</div>
			<hr/>
			<div class="p-3">
				${BBS.b_content}
			</div>
	</section>
	<div class="form-group d-flex justify-content-end">
		<button class="btn btn-primary mr-2">수정</button>
		<button class="btn btn-danger mr-2">삭제</button>
		<button class="btn btn-success">목록으로</button>
	</div>
	<section class="container-fluid bg-light p-4">
		<div class="row p-4">
			<div class="col-2 m-1"><b>홍길동</b></div>
			<div class="col-9 m-1">댓글달기</div>
		</div>
		<div class="row p-4">
			<div class="col-2">
				<input class="form-control" placeholder="작성자">
			</div>
			<div class="col-8">
				<input class="form-control" placeholder="댓글을 입력하세요">
			</div>
			<div class="col-2 d-flex justify-content-start">
				<button class="btn btn-primary mr-2">저장</button>
				<a href="${rootPath}/"><button type="button" class="btn btn-success">목록으로</button></a>
			</div>
		</div>
	</section>
</body>
</html>


