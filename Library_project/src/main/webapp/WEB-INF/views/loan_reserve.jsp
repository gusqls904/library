<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>도서예약현황</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<a href="loan.do">도서대출현황</a>
<a href="loan_reserve.do">도서예약현황</a>
<a href="loan_history.do">반납이력조회</a>
<a href="main2.do">목록으로</a>
<br><br>

	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>예약일</td>
			<td>예약만기일</td>
			<td>예약순번</td>
			<td>예약/대출 상태</td>
			<td>선택</td>
		</tr>
		
		<c:forEach var="list" items="${list}" varStatus="status">
		<input type="hidden" id="book_id" value="${list.book_id}">
		
		<tr>
			<td>${status.count}</td>
			<td>${list.book_name}</td>
			<td>${list.reserve_date}</td>
			<td>${list.return_reserve}</td>
			<td>1 번째</td>
			<c:choose>
			<c:when test="${list.state == 1}">
			<td>대여중</td>
			</c:when>
			<c:when test="${list.state == 0}">
			<td>예약 대기중</td>
			</c:when>
			</c:choose>
			<td><input type = "button" value="예약취소" 
			onclick="cancelreserve('${list.book_id}')"></td>
		<tr>
		</c:forEach>
	</table>

</body>

<script type="text/javascript">
function cancelreserve(book_id){

	alert("예약 취소");
	
 window.location.href='cancelreserve.do?book_id=' + book_id;
}

</script>


</html>