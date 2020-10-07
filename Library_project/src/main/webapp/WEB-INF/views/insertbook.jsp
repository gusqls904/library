<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form action="insert.do" method="post" enctype="multipart/form-data">
이미지 : <input type="file" name="report" id="report" ><br>
도서이름 : <input type="text" id="book_name" name="book_name"><br>
저자 : <input type="text" id="author" name="author"><br>
출판사 : <input type="text" id="publisher" name="publisher"><br>
발행 년도 : <input type="text" id="issueyear" name="issueyear"><br>
<input type="submit" value="저장">
</form>

<br>
<form action="main2.do">
<input type="submit" value="목록으로">
</form>



</body>
</html>