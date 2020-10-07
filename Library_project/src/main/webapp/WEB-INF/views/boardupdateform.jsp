<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- include libraries(jQuery, bootstrap) -->
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>
<title>글쓰기</title>

<script>
$(document).ready(function() {
	  $('#summernote').summernote({
	        minHeight: 370,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  });
	});
</script>

<script>
function save_confirm(){
    if (confirm('수정 하시겠습니까?')) {
         // Yes click
		var bno = ${boarderlist.bno};
	 	var content = $("#summernote").val();
		var title = $("#title").val();
         $.ajax({
             type : "get",
             url : "boardupdate.do",
             data : "title=" + title+"&content="+content+"&bno=" + bno,
             dataType : "html"
         }).done(function(data){
        	 alert('수정완료 되었습니다.')
        	 opener.location.reload();
        	 self.close();
 	 
         }
      );      
    } else {

    }
}



</script>


</head>
<body>
<h2 style="text-align: center;">글 수정</h2><br><br><br>

<div style="width: 60%; margin: auto;">
		작성자:<input type="text" name="writer" value="${boarderlist.writer}" style="width: 20%;"readonly="readonly"/><br>
		제목:<input type="text" name="title" id="title" style="width: 40%;" value="${boarderlist.title}"/>
		<br><br> 
		<textarea id="summernote" name="content">${boarderlist.content}</textarea>
		<input type="button" value="수정취소" onclick="history.go(-1)" style="float: right;">
		<input type="button" id="subBtn" value="수정" style="float: right;" onclick="save_confirm()"/>
</div>
</body>
</html>