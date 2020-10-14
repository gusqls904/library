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

<select id="keyword" name="keyword">
	<option value="all">전체</option>
	<option value="bookname">제목</option>
	<option value="author">저자</option>
	<option value="publisher">출판사</option>
</select>
	<input type="text" id="search" name="search">
	<input type="button" onclick="searchClick()" value="검색">

<script type="text/javascript">

	function searchClick(){
		var search = $("#search").val();
		var keyword = $("#keyword option:selected").val();
		console.log(keyword);
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

	window.location.href='book_search.do?search=' + search + "&keyword=" +keyword;
}
</script>
</body>
</html>