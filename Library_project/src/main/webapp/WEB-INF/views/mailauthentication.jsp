<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
인증번호 입력<input type="text" name="mail_authentication">
<input type="button" value="인증번호 확인" onclick="mailcheck ()"><br>

<script>
var mail_authentication= $("#mail_authentication").val();
var disc;
function mailcheck(String){
		if(mail_authentication==disc){
 			alert('인증완료')
 			}
 		else{
 			console.log(disc);
 			alert('인증실패')
	}
}
</script>
</body>
</html>