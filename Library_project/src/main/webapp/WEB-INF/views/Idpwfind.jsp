<%@ page contentType="text/html; charset=utf-8"%>
<%@ page session="false"%>
<html>
<title>이메일 인증</title>
<head>
</head>
<h1>비밀번호 찾기</h1>
<body>
	가입한 이메일
	<input type="text" name="email" id="email">
	<br> 
	가입한 이름<br>
	<input type="text" name="name" id="name">
	<br>
	<input type="button" value="인증메일 보내기" onclick="namemailcheck()">
	<div id="info"></div>
	<div id="user_id" style="display: none;">
		인증번호 입력:<input type="text" name="emailcheck" id="emailcheck">
		<input type="button" value="인증번호 확인" onclick="mailcheck()"><br>
	</div>
	<div id="info2"></div>

	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
function namemailcheck(){
          var email = $("#email").val(); 
          var name = $("#name").val();
          $.ajax({
              type : "POST",
              url : "namemailcheck.do",
              data : "email=" + email + "&name=" + name,
              success : function(data, textStatus, xhr) {
                  console.log(data);
                  if(email == "" && name == ""){
                	  $("#info").empty();
                	  $("#info").append('이메일과 이름을 입력해주세요');
                    }
                  else if (data == 0) {
                      $("#info").empty();
                	  $("#info").html('가입하신 이메일과<br>이름이 맞지 않습니다');
                      $('#email').val('');
                      $('#name').val('');
                      jQuery('#user_id').css("display", "none");
                      }	
                   else {

                	  $("#info").empty();
                	  $("#info").html('가입하신 이메일로<br> 인증번호를 발송하였습니다.');
	                  $.ajax({
			              type : "POST",
			              url : "send.do",
			              data : "emailadd=" + email,
			              success : function(data) {
				              jQuery('#user_id').css("display", "block"); 
				              console.log(data)
			              }                
                  	  })
                  }
              }
          })
};


$("info").empty();
$("info").append("");


var login;

		function mailcheck() {
			var emailcheck = $('#emailcheck').val();
			$.ajax({
				type : "post",
				url : "mailcheck.do",
				dataType : "text"
			}).done(function(data) {
				if (data == emailcheck) {
					alert('인증 성공.')
					window.location.href = "pwchacge.do";
				} else {
					$("#info2").empty();
					$("#info2").html('인증번호가 다릅니다');
				}
			});
		}
	</script>
</body>
</html>
