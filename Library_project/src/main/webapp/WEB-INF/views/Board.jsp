<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
</head>

<style>
   h2 {
      text-align: center;
   }
   table {
      width: 100%;
   }
   #outter {
      display: block;
      width: 60%;
      margin: auto;
   }
</style>
<body>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
function selectClick(){
   var search = $("#search").val();
   var select =  $("#select option:selected").val();
      $.ajax({      
         type : "post",
         url : "boardselect.do",
         data : "select=" + select + "&search=" + search,
         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
         dataType : "json"
         }).done(function(data){
            console.log(data);
            $("table").empty();
            $("table").append("<tr><td>No.</td><td width='75%'>제목</td><td>작성자</td><td>작성일</td><td>조회수</td></tr>");      
               for(var i = 0; i<data.length; i++){
                  $("table").append("<tr><td>"+data[i].bno+"</td>"+
                  "<td><a href='javascript:void(0);' onClick=window.open('viewDetaill.do?bno="+data[i].bno+"','window팝업','width=1000,height=800,menubar=no,status=no,toolbar=no');>"
                  +data[i].title+"</td>"+
                  "<td>"+data[i].writer+"</td>"+
                  "<td>"+data[i].regdate+"</td>"+
                  "<td>"+data[i].viewcnt +"</td>");      
                  }
            }).fail(function(e) {    
               alert(e.responseText);
            });
   }


function searchClick(){
	   var search = $("#search").val();
	   var select =  $("#select option:selected").val();
	   window.location.href = 'boardsearch.do?search=' +search + '&select='+select;
	}




function searchClick2(){
	window.location.href = 'boardsearch.do?search='+search + 'select='+select;
}



function viewcntup(bno,viewcnt){
   	
      var str = ""
      var tdArr = new Array();    
      var checkBtn = $(bno);
      
         var tr = checkBtn.parent().parent();
         var td = tr.children();
         var view = td.eq(4).text();
         
         
   window.open('viewDetaill.do?bno='+bno,'window팝업','width=600, height=600, menubar=no, status=no, toolbar=no');
   }


function reflash(){
	location.reload(true);
}



</script>





<h2>게시판</h2>
<div style="margin-left: 1228px;">
<select id="select" name="select">
   <option value="all">전체</option>
   <option value="title">제목</option>
   <option value="writer">글쓴이</option>
</select>
   <input type="text" id="search" name="search">
   <input type="button" onclick="searchClick()" value="검색">
   </div>
<div id="outter">
   <table border="1">
      <tr>
         <td>No.</td>
         <td width="50%">제목</td>
         <td>작성자</td>
         <td>등록일</td>
         <td>조회수</td>      
      </tr>
      <c:forEach items="${boardList.boardInfoList}" var="lista" varStatus="status">
         <tr>
            <td>${number}</td>
            <c:set var="number" value="${number-1}"/>
            <td width='75%'><a href='javascript:void(0);' onClick="viewcntup(${lista.bno},${lista.viewcnt}); reflash()">${lista.title }</a></td>
            <td>${lista.writer } </td>
            <td>${lista.regdate } </td>
            <td id="test${number+1}">${lista.viewcnt}</td>
         </tr>
      </c:forEach>
   </table>
</div>
<input type="button" value="전페이지 가기" onclick="location.href='main2.do'"style="margin-left: 1360px; margin-top: 10px;">
<input type="button" value="글쓰기" onclick="location.href='WriteForm.do'">

       <style type="text/css">
         li {list-style: none; float: left; padding: 6px;}
      </style>
</head>
<div align="center">
            <c:if test="${boardList.count > 0}">
               <c:if test="${boardList.p.beginPageNumber > 10}">
                  <a href="boardsearch.do?p=${boardList.p.beginPageNumber-1}">이전</a>
               </c:if>
               <c:forEach var="pno" begin="${boardList.p.beginPageNumber}" end="${boardList.p.endPageNumber}">
                  <a href="boardsearch.do?p=${pno}">[${pno}]</a>
               </c:forEach>
               <c:if test="${boardList.p.endPageNumber < boardList.p.totalPageCount}">
                  <a href="boardsearch.do?p=${boardList.p.endPageNumber + 1}">다음</a>
               </c:if>
            </c:if>
         </div>
</body>
</html>