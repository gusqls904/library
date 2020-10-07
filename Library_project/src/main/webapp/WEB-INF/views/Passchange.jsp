<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<html>
<head>
	<title>메일인증/비밀번호변경</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>


function checkIt() { 
	 var pass1 = $("#user_pw_sub").val();
	 var pass2 = $("#pw_sub_chk").val();
		if(pass1 == "" && pass1 == ""){
			$("#pw_sub_check").empty();
		  	$("#pw_sub_check").html('변경할 비밀번호를 입력하세요');
			return false;
		}

		if(pass1 !=null && pass2==""){
			$("#pw_sub_check").empty();
		  	$("#pw_sub_check").html('비밀번호를 확인해주세요.');
		  	return false;
		}
		if(pass1 != pass2){
			$("#pw_sub_check").empty();
		  	$("#pw_sub_check").html('비밀번호가 다릅니다.');
		  	return false;
		}
	}
		

/* $(function() {
	$('#pw_sub_chk').blur(function() { // 비밀번호 확인
	    var pw_sub_chk = $("#user_pw_sub").val();
	    var pw_chk = $("#pw_sub_chk").val();
	    if (pw_sub_chk == "" || pw_sub_chk == null) {
	    	 $("#pw_sub_check").empty();
	         $("#pw_sub_check").html("필수정보 입니다.");
	      }else if (pw_sub_chk != "" || pw_sub_chk != null) {
	          $("#pw_sub_check").html("");
	      }
	      if(pw_sub_chk != pw_chk) {
	    	 $("#pw_sub_check").empty();
	         $("#pw_sub_check").html("비밀번호가 일치하지 않습니다.");
	      } else if(pw_sub_chk != pw_chk) {
	         $("#pw_sub_check").html("");
	      }
	});
}) */

</script>
<form action="pwupdate.do" onsubmit="return checkIt()">
변경할 비밀번호:<input type="password" name="user_pw_sub" id="user_pw_sub"><br>
비밀 번호 확인:<input type="password" name="password" id="pw_sub_chk"><br>
<input type="submit" name="passchangeupdate" id="passchangeupdate" value="비밀번호 변경">
<div id="pw_sub_check"></div>
</form>
</body>
</html>
