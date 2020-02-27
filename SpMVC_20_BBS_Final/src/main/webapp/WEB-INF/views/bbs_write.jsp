<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf"%>
<script>
	$(function() {

		$(".btn-delete").click(function(){
			//let b_id = $(this).data("b_id")
			document.location.href = "${rootPath}/delete?b_id=${BBS.b_id}"
		})
		
		$(".btn-list").click(function(){
			document.location.href = "${rootPath}/list"
		})
		
		
	var toolbar = [
		['style',['bold','italic','underline'] ],
		['fontsize',['fontsize']],
		['font Style',['fontname']],
		['color',['color']],
		['para',['ul','ol','paragraph']],
		['height',['height']],
		['table',['table']],
		['insert',['link','hr','picture']],
		['view',['fullscreen','codeview']]
	]
	
	$("#b_content").summernote({
		lang:'ko-KR',
		placeholder:'본문을 입력하세요',
		width:'100%',
		toolbar:toolbar,
		height:'200px',
		disableDragAndDrop : false,
		callbacks : {
			onImageUpload : function(files,editor,isEdite){
				for(let i = files.length - 1 ; i >= 0  ; i--){
					// 파일을 1개씩 업로드할 함수
					upFile(files[i],this)// this = editor
				}
			}
		}
	})//end summer
	
	// ajax를 사용해서 파일을 서버로 업로드 수행하고
	// 실제 저장된 파일 이름을 받아서
	// summernote에 기록딘 내용중 img src=""을 변경
	function upFile(file,editor){
			var formData = new FormData()
			// upFile 변수에 file 정보를 담아서 보내기 위한 준비
			/*
			 editor.insertImage
			 summernote의 내장 함수를 callback형
			 호출해서 현재 summernote box에 표시
			 이미지의 src 부분을 url 값으로 대치
			 
			 img src="data:base64~~~~"
			 img src="UUID파일.jpg" 형태로 변형
			*/
			formData.append('upFile',file)
			$.ajax({
				url : "${rootPath}/image_up",
				type : "POST",
				data : formData,
				contentType : false,
				processData : false,
				enctype : "multipart/form-data",
				success : function(result){
					alert(result)
					result = "${rootPath}/files/" + result
					$(editor).summernote('editor.insertImage',result)
				},
				error : function(){
					alert("서버통신오류")
				}
				
			})
		}
		
	})
</script>
</head>


<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf"%>
	<section class="container-fluid">
		<fieldset>
			<form method="POST">
				<div class="form-group">
					<input name="b_writer" class="form-control" placeholder="작성자"
					value="${BBS.b_writer}">
				</div>
				
				<div class="form-group">
					<input name="b_subject" class="form-control" placeholder="제목"
					value="${BBS.b_subject}">
				</div>
				<div class="form-group">
					<textarea id="b_content" name="b_content" rows="10" cols="30">${BBS.b_content}</textarea>
				</div>
				<div class="form-group d-flex justify-content-end">
					<button class="btn btn-primary mr-2">저장</button>
					<button type="button" class="btn btn-danger mr-2 btn-delete">삭제</button>
					<button type="button" class="btn btn-success btn-list">목록으로</button>
				</div>
			</form>
		</fieldset>

	</section>

</body>
</html>