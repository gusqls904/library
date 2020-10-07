<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<td>반납일</td>
		</tr>
		<c:forEach var="list" items="${list}" varStatus="status">
			<input type="hidden" id="book_id" value="${list.book_id}">
			<tr>
				<td>${status.count}</td>
				<td>${list.book_name}</td>
				<td>${list.loan_date}</td>
				<td>${list.return_date}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>