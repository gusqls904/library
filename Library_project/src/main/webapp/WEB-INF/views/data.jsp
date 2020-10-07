<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 검색/예약</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>

<h2>도서검색</h2>

<a href="main2.do">목록으로</a><br><br>

<select id="op" name="op">
	<option value="all">전체</option>
	<option value="bookname">제목</option>
	<option value="author">저자</option>
	<option value="publisher">출판사</option>
</select>
	<input type="text" id="select" name="select">
	<input type="button" onclick="selectClick()" value="검색">

	<div id="selectInfo"></div>

<script type="text/javascript">

	function selectClick(){
var select = $("#select").val();
var select1 = $("#op option:selected").val();
	console.log(select1);
/* 	$.ajax({
		
		type : "post",
		url : "select.do",
		data : "select=" + select,
		dataType : "html"
		}).done(function(data){
			$('#selectInfo').html(data);
			}).fail(function(e) {
				alert(e.responseText);
			}); */

	window.location.href='select.do?select=' + select + "&select1=" +select1;
}
</script>
</body>
</html>