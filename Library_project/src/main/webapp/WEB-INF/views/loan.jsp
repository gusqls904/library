<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>

<a href="loan.do">도서대출현황</a>
<a href="loan_reserve.do">도서예약현황</a>
<a href="loan_history.do">반납이력조회</a>
<a href="main2.do">목록으로</a>

<br>
<br>
	<table border=1>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>대출일</td>
			<td>반납예정일</td>
			<td>상태</td>
			<td>연체일수</td>
			<td>반납</td>
			<td>반납연기</td>
		</tr>
		
			<c:forEach var="list" items="${list}" varStatus="status">
			<input type="hidden" id="book_id" value="${list.book_id}">
		<tr>
				<td>${status.count}</td><!-- 수정중  -->
				<td>${list.book_name}</td>
				<td>${list.loan_date}</td>
				<td>${list.term}</td>
				
				<c:choose>
				<c:when test="${list.state == 1}">
				<td>대여중</td>
				</c:when>
				<c:when test="${list.state == 0}">
				<td> </td>
				</c:when>
				</c:choose>
				
				<c:choose>
				<c:when test="${list.datediff > 0}">
				<td>${list.datediff}</td>
				</c:when>
				<c:when test="${list.datediff < 0}">
				<td>0</td>
				</c:when>
				</c:choose>
				<td><input type="button" value="반납하기" onclick="returnbook('${list.book_id}')"></td>
				<td><input type="button" value="반납연기신청" onclick="bookextension('${list.book_id}')" /></td>
		</tr>
			</c:forEach>
	</table>
	   <br> 

</body>

<script type="text/javascript">

	function returnbook(book_id){

		alert("반납 완료");
		
	 window.location.href='returnbook.do?book_id=' + book_id;
}

	function bookextension(book_id){

		var url = "bookextension.do"; 
		
		$.ajax({
			type : "post",
			url :url,
			data : "book_id=" + book_id
			}).done(function(args){
					
				console.log(args);	
					
				if(args=="1"){
					alert('예약된 도서는 연장 불가능.');
					}
				if(args=="0"){
					alert('기간 연장 완료.');
					}
				location.reload(true);
			}).fail(function(e){
				alert(e.responText);
			})
	}
	 /* window.location.href='bookextension.do?book_id=' + book_id; */

	

</script>
</html>