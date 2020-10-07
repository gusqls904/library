<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세보기</title>
</head>
<style>
	h2 { text-align: center;}
  table { width: 100%;}
  textarea { width: 100%;}
 	#outter {
		display: block;
		width: 70%;
		margin: auto;
	}
</style>
<body>

<h2>게시글 상세보기</h2>
<br><br><br>

<div id="outter">
	<table border="1">
		<tr>
			<td>제목:${board.title}</td>
		</tr>
		<tr>
			<td>
				작성자:${board.writer}
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;작성일:${board.regdate}
			</td>
		</tr>
		<tr>
			<td><div style="height: 300px; margin: 10px; display: inline-block">
			${board.content}</div></td>
		</tr>
	</table>
	<input type="button" value="닫기" style="float: right;" onclick='self.close()'> 
	<input type="button" value="삭제" style="float: right;" onclick='boarddelete(${board.bno})'> 
	<input type="button" value="수정" style="float: right;" onclick='boardupdateform(${board.bno})'> 
</div>


<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>

//게시물 삭제 부분
function boarddelete(bno){
	if (confirm('삭제하시겠습니까?')) {
		   $.ajax({
	             type : "get",
	             url : "boarddelete.do",
	             data : "bno=" + bno,
	             dataType : "html"
	         }).done(function(data){
	        	 alert('삭제완료 되었습니다.')
	        	 opener.location.reload();
	        	 self.close();
	         }
	         );
	} else {
	}
}


//게시물 수정 부분
function boardupdateform(bno){
	 location.href = "boardupdateform.do?bno="+bno;
	}

</script>
</body>
</html>