<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>

<a href="loan.do">도서대여현황</a>
<a href="loan_reserve.do">도서예약현황</a>
<a href="loan_history.do">반납이력조회</a>
<a href="main.do">목록으로</a>
<br><br>

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
				<input type="hidden" id="term" value="${list.term}">
				<input type="hidden" id="arrears" value="${list.arrears}">
				<input type="hidden" id="datediff" value="${list.datediff}">
			<tr>
				<td>${status.count}</td>
				<td>${list.book_name}</td>
				<td>${list.loan_date}</td>
				<td id='term${status.count}'>${list.term}</td>
	
				<c:choose>
					<c:when test="${list.state == 1}">
						<td>대여중</td>
					</c:when>
					<c:when test="${list.state == 0}">
						<td> </td>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test="${list.arrears > list.datediff}">
						<td>${list.arrears - list.datediff}일</td>
					</c:when>
					<c:when test="${list.arrears <= list.datediff}">
						<td>0일</td>
					</c:when>
				</c:choose>
				
						<td><input type="button" value="반납하기" onclick="returnbook('${list.book_id}')" /></td>
						
				<c:choose>
					<c:when test="${list.extension == 1 || list.arrears - list.datediff >= 1}">
						<td><input type="button" value="반납연기신청" onclick="bookextension('${list.book_id}')" disabled="disabled" /></td>
					</c:when>
					<c:when test="${list.extension == 0 }">
						<td><input type="button" id="onebtn" value="반납연기신청" onclick="bookextension('${list.book_id}')" /></td>
					</c:when>
				</c:choose>
			</tr>
			</c:forEach>
	</table>
	
</body>

<script type="text/javascript">

	function returnbook(book_id){
		window.location.href='returnbook.do?book_id=' + book_id;
		alert("반납 완료");
}

	function bookextension(book_id){
		var url = "bookextension.do";
		$.ajax({
			type : "post",
			url :url,
			data : "book_id=" + book_id
			}).done(function(args){
				if(args=="0"){
						alert('기간 연장 완료.');
						location.reload(true);
					}
				
				if(args=="2"){
					alert('예약한 계정이 있습니다.');
					}
			}).fail(function(e){
				alert(e.responText);
			})
			 //window.location.href='bookextension.do?book_id=' + book_id;	(임시) 
	}

</script>
</html>