<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<form method="POST" class="repl">
	<div class="row p-4">
		<input type="hidden" name="c_p_id" id="c_p_id" value="${CMT.c_p_id}"> <input
			type="hidden" name="c_b_id" value="${CMT.c_b_id}">
		<div class="col-2">
			<input class="form-control" name="c_writer" id="c_writer" placeholder="작성자">
		</div>
		<div class="col-8">
			<input class="form-control" name="c_subject" id="c_subject" placeholder="댓글을 입력하세요">
		</div>
		<div class="col-2 d-flex justify-content-start">
			<button type="button" class="btn btn-primary mr-2 btn-cmt-save">답변저장</button>
			<a href="${rootPath}/"><button type="button"
					class="btn btn-success">목록으로</button></a>
		</div>
	</div>
</form>