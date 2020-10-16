<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.library.wol.model.DataDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
</head>
<body>
<h2>도서검색</h2>

<%  String search = request.getParameter("search"); 
	String keyword = request.getParameter("keyword");
	if(keyword == null) keyword = "";
%>
<a href="main2.do">목록으로</a><br><br>
<select id="keyword" name="keyword">
	<option value="all">전체</option>
	<option value="bookname" <%=keyword.equals("bookname")|| keyword == null?"selected":""%>>제목</option>
	<option value="author" <%=keyword.equals("author")|| keyword == null?"selected":""%>>저자</option>
	<option value="publisher" <%=keyword.equals("publisher")|| keyword == null?"selected":""%>>출판사</option>
</select>
	<input type="text" id="search" name="search" size=40 value="<%= search %>">
	<input type="button" onclick="searchClick()" value="검색">

	<br><br>
 	<c:forEach var="list" items="${list}">
 	<input type="hidden" id="book_id" value="${list.book_id}">
 	<input type="hidden" id="reserve_no" value="${list.reserve_no}">
 	<img src= "${list.image}" height="120" width="120"><br>
		제목 : ${list.book_name}<br>
		저자 : ${list.author}<br>
		출판사 : ${list.publisher}<br> 
		발행년도 : ${list.issueyear}년<br>
		상태 :
		<c:choose>
		<c:when test="${list.state == 0 and list.reserve_no == user_id}">
			예약중 (대여가능)<br>
			<input type="button" value="대여하기" disabled="disabled">
			<input type="button" value="예약하기" disabled="disabled">
			<input type="button" value="예약도서대여" id="${list.book_id}" onclick="reserveRental('${list.book_id}')">
		</c:when>
		
		<c:when test="${list.state == 1 and list.reserve_no == '0'}">
		 	대여중 (예약 : 0명)(반납예정일 ${list.term})<br>
		 	<input type="button" value="대여하기" disabled="disabled">
		 	<input type="button" value="예약하기" 
		 	onclick="BookReserve('${list.book_id}',${list.reserve_no})">
		 	
		</c:when>
		
		<c:when test="${list.state == 1 and user_id != '0'}">
		 	대여중 (예약 : 1명)(반납예정일 ${list.term})<br>
		 	<input type="button" value="대여하기" disabled="disabled">
		 	<input type="button" value="예약하기" disabled="disabled">
		</c:when>
		
		<c:when test="${list.state == 0 and list.reserve_no == '0'}">
		 	대여가능<br>
		 	<input type="button" value="대여하기" id="${list.book_id}" onclick="BookRental('${list.book_id}')">
		 	<input type="button" value="예약하기" disabled="disabled">
		</c:when>
		
		<c:when test="${list.state == 0 and list.reserve_no != user_id}">
			예약중 (예약 : 1명)<br>
			<input type="button" value="대여하기" disabled="disabled">
			<input type="button" value="예약하기" disabled="disabled">
		</c:when> 
		
		</c:choose>		
		<br><br>
		
		</c:forEach>  
		
		<br><br><br>
		
<div align="left">
      		<c:if test="${boardList.count > 0}">
               <c:if test="${boardList.p.beginPageNumber > 10}">
                  <a href="Board.do?p=${boardList.p.beginPageNumber-1}">이전</a>
               </c:if>
               <c:forEach var="pno" begin="${boardList.p.beginPageNumber}" end="${boardList.p.endPageNumber}">
                  <a href="javascript:void(0);" onclick="pagingboardajax(${pno});">[${pno}]</a>
               </c:forEach>
               <c:if test="${boardList.p.endPageNumber < boardList.p.totalPageCount}">
                  <a href="Board.do?p=${boardList.p.endPageNumber + 1}">다음</a>
               </c:if>
            </c:if>
         </div>
</body>
</body>

<script type="text/javascript">

function searchClick(){
	var search = $("#search").val();
	var keyword = $("#keyword option:selected").val();
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

function BookRental(book_id){
 	
  	var url = "loaninsert.do";
	
/* 	$.ajax({
		type : "post",
		url : url,
		data : "book_id=" + book_id,
		dataType : "json"
		}).done(function(data){

			for(int i = 0 ; i < data.length;++i){
				console.log(data[i]);
				}
			console.log(data);										
			alert("대여완료");
			
			}).fail(function(e){
				alert(e.responseText);
				}); */    
				
 		
  	window.location.href='loaninsert.do?book_id=' + book_id;
	alert("대여 완료")
	} 

function reserveRental(book_id){
 	
	/*  var url = "loaninsert.do";
	
 	$.ajax({
		type : "post",
		url : url,
		data : "book_id=" + book_id,
		dataType : "json"
		}).done(function(data){

			for(int i = 0 ; i < data.length;++i){
				console.log(data[i]);
				}
			console.log(data);										
			alert("대여완료");
			
			}).fail(function(e){
				alert(e.responseText);
				}); */    
				
 		
  	window.location.href='reserveRental.do?book_id=' + book_id;
	alert("대여 완료")
	} 

function BookReserve(book_id,reserve_no){   
	window.location.href='BookReserve.do?book_id=' + book_id;	
	}



function pagingboardajax(pno){
	$.ajax({		
		type : "get",
		url : "pagingboardajax.do",
		data : "p=" + pno,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType : "json"
		}).done(function(data){
			console.log("넘버는"+data.number);
			console.log(data.boardList.boardInfoList.length);
		$("table").empty();
			$("table").append("<tr><td>No.</td><td width='20%'>제목</td><td>작성자</td><td>작성일</td><td>조회수</td></tr>");		
			 var list = data.boardList.boardInfoList;
			 var number = data.number;
			for(var i = 0; i<list.length; i++){
					$("table").append("<tr><td>"+number+"</td>"+
					"<td><a href='javascript:void(0);' onClick=window.open('viewDetaill.do?bno="+list[i].bno+"','window팝업','width=1000,height=800,menubar=no,status=no,toolbar=no');>"
					+list[i].title+"</td>"+
					"<td>"+list[i].writer+"</td>"+
					"<td>"+list[i].regdate+"</td>"+
					"<td>"+list[i].viewcnt);
					number--;
					} 
			}).fail(function(e) {
				alert(e.responseText);
			});
}
	
</script>
</html>