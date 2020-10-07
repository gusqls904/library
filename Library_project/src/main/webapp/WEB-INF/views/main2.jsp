<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello Library!  
</h1>

${user_id}님 환영합니다.



<input type="button" id="logout" name="logout" value="로그아웃" onclick='location.href="logout.do";'><br>
<input type="button" id="logout" name="logout" value="내정보 수정"
onclick="window.open('profile.do','window팝업','width=500, height=500, menubar=no, status=no, toolbar=no');">
<br>
<a href="data.do">도서 검색/예약</a><br>
<a href="loan.do">마이페이지</a><br>
<a href="insertbook.do">신규 도서 등록</a><br>



<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>

	function selectClick(){
		   var search = $("#search").val();
		   var select =  $("#select option:selected").val();
		      $.ajax({      
		         type : "post",
		         url : "boardselectajax.do",
		         data : "select=" + select + "&search=" + search,
		         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		         dataType : "json"
		         }).done(function(data){
		            console.log(data);
		            $("table").empty();
		            $("table").append("<tr><td width='20'>No.</td><td width='1000'>제목</td><td>작성자</td><td>작성일</td><td>조회수</td></tr>");
		             var list = data.boardList1;
		             var list1 = data.boardList;
		             var page = data.boardList.p;
		             
		             var number = data.number;
		             for(var i = 0; i<list.length; i++){
		                  $("table").append("<tr><td width='20'>"+number+"</td>"+
		                  "<td width='1000'><a href='javascript:void(0);' onClick=window.open('viewDetaill.do?bno="+list[i].bno+"','window팝업','width=600,height=600,menubar=no,status=no,toolbar=no');>"
		                  +list[i].title+"</td>"+
		                  "<td width='100'>"+list[i].writer+"</td>"+
		                  "<td>"+list[i].regdate+"</td>"+
		                  "<td>"+list[i].viewcnt);
		                  number--;
		                  }
		             $("#paging").empty();
		             var str = "<div align='left'>";

		             if(list1.count > 0){
	                     if (page.beginPageNumber > 10){
	                    	 str += "<a href='Board.do?p="+(page.beginPageNumber-1)+"'>이전</a>"
	                  		}	
	                     for(var pno = page.beginPageNumber; pno <= page.endPageNumber; pno++){
	                    	 str += "<a href='javascript:void(0);' onclick='pagingboardajax("+pno+");'> ["+pno+"] </a>"
	                     }
	                     if(page.endPageNumber < page.totalPageCount){
	                    	 str += "<a href='Board.do?p="+(page.endPageNumber + 1)+"'>다음</a>"
	                     }
	                  }
	             		str += "</div>"
		                 
		             $("#paging").append(str);
		             
		               
		            }).fail(function(e) {    
		               alert(e.responseText);
		            });
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
			$("table").append("<tr><td width='20'>No.</td><td width='75%'>제목</td><td>작성자</td><td>작성일</td><td>조회수</td></tr>");		
			 var list = data.boardList.boardInfoList;
			 var number = data.number;
			for(var i = 0; i<list.length; i++){
					$("table").append("<tr><td width='20'>"+number+"</td>"+
					"<td width='75%'><a href='javascript:void(0);' onClick=window.open('viewDetaill.do?bno="+list[i].bno+"','window팝업','width=1000,height=800,menubar=no,status=no,toolbar=no');>"
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
   

<h2>게시판</h2>

<select id="select" name="select">
	<option value="all">전체</option>
	<option value="title">제목</option>
	<option value="writer">글쓴이</option>
</select>
	<input type="text" id="search" name="search">
	<input type="button" onclick="selectClick()" value="검색">
	<input type="button" onclick="location.href='Board.do'" value="게시판 더보기">
	<input type="button" value="글쓰기" onclick="location.href='WriteForm.do'"/><br>
<div id="outter">
	<table border="1">
		<tr>
			<td width='20'>No.</td>
			<td width='1000'>제목</td>
			<td width='100'>작성자</td>
			<td>작성일</td>
			<td>조회수</td>	
		</tr>
			<c:forEach items="${boardList.boardInfoList}" var="lista">
			<tr>
				<td>${number}</td>
				<c:set var="number" value="${number-1}"/>
				<td><a href='javascript:void(0);' onClick="window.open('viewDetaill.do?bno=${lista.bno}','window팝업','width=1000, height=800, menubar=no, status=no, toolbar=no');">${lista.title }</td>
				<td>${lista.writer } </td>
				<td>${lista.regdate } </td>
				<td>${lista.viewcnt }</td>
			</tr>
		</c:forEach>
		
	</table>
</div>
<div align="left" name="paging" id="paging">
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
</html>
