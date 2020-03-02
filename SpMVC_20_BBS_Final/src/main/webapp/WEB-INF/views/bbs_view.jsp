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
		
		//$(".cmt-item").click(function(){
		$(document).on("click",".cmt-item",function(){
			let id = $(this).data("id")
			
			let writer = $(this).find("div.writer").text()
			let subject = $(this).find("div.subject").text()
			
			$("#c_id").val(id)
			$("#c_writer").val(writer)
			$("#c_subject").val(subject)
		})
		
		$(document).on("click","div.cmt-item-del",function(event){
			
			// 나를 감싸고 있는 곳으로 이벤트가 전파되는 것을 그만둬라
			// (cmt-item 으로 가는걸 그만둬라)
			event.stopPropagation()
			if(!confirm("코멘트를 삭제할까요?")){
				return false
			}
			
			// $(this).closest("div")
			// 현재 자신을 감싸고 있는 가장 가까운 div를 찾아라
			let c_id = $(this).parent("div").data("id")
			$.ajax({
				url : "${rootPath}/comment/delete/",
				data : {
					c_id : c_id,
					b_id : "${BBS.b_id}"
					},
				
				type : "POST",
				success : function(result){
					$("div.cmt-list").html(result)
				},
				error : function(){
					alert("서버 통신오류")
				}
				
				})
			
		})
		
		$(document).on("click",".cmt-item-repl",function(event){
			
			let b_id = "${BBS.b_id}"
			let c_id = $(this).parent("div").data("id")
			let data = {c_b_id:b_id,c_p_id:c_id}
			
			event.stopPropagation()
			
			$.get("${rootPath}/comment/repl",data,function(result){
				
				$(".modal-body").html(result)
				$(".modal-main").css("display","block")
			
			})
		
		})
		
		
		$(document).on("click","button",function(){
			
			let txt = $(this).text()
			
			if(txt == '수정'){
				document.location.href="${rootPath}/update?b_id=${BBS.b_id}"
			}else if(txt == '삭제'){
				if(confirm("삭제할까요?")){
					document.location.replace("${rootPath}/delete/${BBS.b_id}")
				}
				
			}else if(txt == '답변저장'){
				
				var formData = $("form.repl").serialize()
				$.ajax({
					
					url : "${rootPath}/comment/write",
					data : formData,
					type : "POST",
					success : function(result){
						$(".modal-main").css("display","none")
						$("div.cmt-list").html(result)
					},
					error : function(){
						alert("서버와 통신오류")
					}
					
				})
				
			}else if(txt == '저장'){
				
				/*
				ajax를 사용해서 form에 담긴 데이터를 controller로 전송
				.serialize() 함수를 사용해서 form에 담긴 데이터를 문자열화하고, query형식으로
				변경하고 ajax의 data에 담아서 전송
				*/
				// input box에 입력한 데이터를 json 형태로 변경하기
				// 항목이 많을 경우 문제를 일으킬수 있다
				var aData = {
					c_wirter : $("#c_wirter").val(),
					c_subject : $("#c_subject").val()
				}
				
				// .serialize()를 사용하면 모든 문제 해결
				var formData = $("form.main").serialize()
				//alert(formData)
				$.ajax({
					
					url : "${rootPath}/comment/write",
					data : formData,
					type : "POST",
					success : function(result){
						$("div.cmt-list").html(result)
					},
					error : function(){
						alert("서버와 통신오류")
					}
					
				})
				
				
				return true
				
			}else if(txt == "답글"){
				//alert("답글쓰기")
				document.location.href = "${rootPath}/repl?b_id=${BBS.b_id}"
				return false
			}else {
				document.location.href="${rootPath}/list"
			}
			
		})
		
		// 코맨트 컨트롤러로 보내줄 저장버튼
		$("#btn-c-write").click(function(){
			
			var c_comment = URLSearchParam(window.location.search)
			var b_id = c_comment.get("b_id")
			
			document.location.href="${rootPath}/comment/write?b_id=" + b_id
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
		<button class="btn btn-info mr-2">답글</button>
		<button class="btn btn-success">목록으로</button>
	</div>
	<section class="container-fluid bg-light p-4">
		<div class="cmt-list">
			<%@ include file="/WEB-INF/views/comment_list.jsp" %>
		</div>
	</section>
	<section class="container-fluid bg-light p-4">
	<form method="POST" class="main">

		<div class="row p-4">
		
				<input type="hidden" name="c_id" id="c_id" value="0">
				<input type="hidden" name="c_b_id" value="${BBS.b_id}">
				
			<div class="col-2">
				<input class="form-control" name="c_writer" id="c_writer" placeholder="작성자">
			</div>
			<div class="col-8">
				<input class="form-control" name="c_subject" id="c_subject" placeholder="댓글을 입력하세요">
			</div>
			<div class="col-2 d-flex justify-content-start">
				<button type="button" class="btn btn-primary mr-2 btn-cmt-save">저장</button>
				<a href="${rootPath}/"><button type="button" class="btn btn-success">목록으로</button></a>
			</div>
		</div>
	</form>
	
	<style>
div.modal-main{
position: fixed;
top:0;
left:0;
width:100%;
height: 100%;
overflow: auto;
background-color: rgba(0,0,0,0.4);
z-index: 10;
display: none;
}
div.modal-content{
width: 80%;
position: relative;
margin: auto;
top:300px;
padding: 0;
}
div.modal-header{
display: flex;
justify-content: flex-end;
}

span.modal-close{
cursor: pointer;
font-size: 30px;

color: #000;
}

span.modal-close:hover, span.modal-close:focus{
color:#000;
font-weight: bold;
}
</style>
<script>
$(function(){
	
	$(".modal-close").click(function(){
		$(".modal-main").css("display","none")
	})
	
})
</script>
<div class="modal-main">
	<div class="modal-content">
		<div class="modal-header">
			<span class="modal-close">&times;</span>
		</div>
		<div class="modal-body">
			
		</div>
	</div>
</div>	
	
	</section>
</body>
</html>


