<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<h1>회원가입</h1>
</head>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>

var authenticationnum;
var idcheck;

	function checkIt() { //유효성 검사
		var user_id = $("#user_id").val();
		var password = $("#password").val();
		var name = $("#name").val();
		var gender = $('input:radio[name=gender]').is(':checked');
		var birth = $("#birth").val();
		var email = $("#email").val();
		var address = $("#address").val();
		
		if (!user_id) {
			alert("id를 입력해주세요.");
			return false;
		}

		if(1!=idcheck){
			alert("아이디 중복체크를 해주세요");
			return false;
			}
		
		if (!password) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		if (!name) {
			alert("이름을 입력해주세요.");
			return false;
		}

		if (gender == false) {
			alert("성별을 입력해주세요.");
			return false;
		}

		if (!birth) {
			alert("생일을 입력해주세요.");
			return false;
		}
		if (!email) {
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		if(1 != authenticationnum){
			alert("이메일을 인증해주세요.");
			return false;
			}
		
		if (!address) {
			alert("주소를 입력해주세요.");
			return false;
		}
	}

	//id 중복체크 부분
	function checkid() {
		var user_id = $("#user_id").val();
		$.ajax({
			type : "POST",
			url : "checkid.do",
			data : "user_id=" + user_id,
			success : function(data) {
				if (user_id == '') {
				$("#idcheck").empty();
               	  $("#idcheck").html('아이디를 입력해주세요');
					$('#user_id').focus();
				} else if (user_id == data) {
				$("#idcheck").html('중복된 id입니다');
				$('#user_id').focus();
				} else if (user_id != data) {
					$("#idcheck").html('사용가능한 id입니다');
					$('#password').focus();
					idcheck = 1;
				}
			}
		})
	}

	

	
	function emailCheck1() {
		var em = $("#email").val();
		var url = 'mailauthentication.do?email=' + em;
		window.open(url, 'window팝업',
				'width=600, height=600, menubar=no, status=no, toolbar=no');
	}
	


	

	function authentication_method() {

		$.ajax({
			type : "post",
			url : "authentication.do",
			dataType : "text"
				}).done(
				function(data) {				
				if ($("#authentication").val() == data){
					$("#mailview").empty();
					$("#mailview").html('인증이 완료되었습니다.');
					document.getElementById("mail_authentication").style.display = "none";
					btn = document.getElementById('authentication_mail');
					btn.disabled = 'disabled';
					authenticationnum = 1;
					}else{
						$("#mailview").empty();
						$("#mailview").html('인증번호가 다릅니다.'); 
						}
					});
				}
	

	


	function mailsend() {
		$("#idcheck").empty();
		var emailadd = $("#email").val();
		if (emailadd == "") {
		   	$("#mailview").html('이메일을 입력하세요.');
			return false;
		}
			if (emailadd != "") {
				$.ajax({
						type : "post",
						url : "send.do",
						data : "emailadd=" + emailadd,
						dataType : "json"
					})
					.done(
						function(data) {
							if(data==true)
								$("#mailview").empty();
								$("#mailview").html('입력하신 메일로 인증메일을 발송하였습니다.');
								document.getElementById("mail_authentication").style.display = "block";
						});
						
			};
		}
	
</script>


<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						var addr = '';
						var extraAddr = ''; 

						if (data.userSelectedType === 'R') { 
							addr = data.roadAddress;
						} else { 
							addr = data.jibunAddress;
						}

						if (data.userSelectedType === 'R') {
	
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
		
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
					
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
				

						} else {
							document.getElementById("sample6_extraAddress").value = '';
						}

						document.getElementById('postcode').value = data.zonecode;
						document.getElementById("address").value = addr;
				
						document.getElementById("detailAddress")
								.focus();
					}
				}).open();
	}
</script>
<body>
	<form action="signinsert.do" onsubmit="return checkIt()">
		ID : <input type="text" id="user_id" name="user_id" placeholder="id">
		   	<input type="button" value="중복확인" onclick="checkid()"><br>
		   	<div id="idcheck"></div>
		PW : <input type="password" id="password" name="password" placeholder="비밀번호"><br>
		
		이름 : <input type="text" id="name" name="name"><br>
		성별 : <input type="radio" name="gender" value="men" >
		남자 <input type="radio" name="gender" value="girl">여자<br> 
		생년월일<input type="date" placeholder="생년월일" id="birth" name="birth"><br>
		이메일:<input type="email" placeholder="email" id="email" name="email">
		<input type="button" name="authentication_mail" id="authentication_mail" value="인증메일 보내기" onclick="mailsend()"><br>
		<div id="mailview"></div>
		<div name="mail_authentication" id="mail_authentication" style="display: none">
			<input type="text" name="authentication" id="authentication" 
			placeholder="인증코드"> <input type="button" 
			value="인증코드 확인" onclick="authentication_method()">
		</div>
		휴대폰번호:<input type="text" placeholder="phone-number" id="phone"
			name="phone"><br> 우편번호:<input type="text"
			id="postcode" placeholder="우편번호"> <input
			type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
		주소:<input type="text" id="address" name="address" placeholder="주소"><br>
		상세주소:<input type="text" id="detailAddress" placeholder="상세주소">
		<input type="submit" value="회원가입">
	</form>

</body>
</html>
