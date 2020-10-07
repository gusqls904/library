<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보 변경</title>

<h1> 회원 정보 수정</h1>
</head>

<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>

$(function() {
	$('#password2').blur(function() {
		var password = $("#password").val();
		var password2 = $("#password2").val();
		if(password != password2){
		      $('#password').val('');
		      $('#password2').val('');
		      $('#password').focus()
	      document.getElementById("pwcheck").style.display = "block";
		}
		else{
		document.getElementById("pwcheck").style.display = "none"; 
		}
	});
});



var authenticationnum;
var idcheck;
var gender = $('input:radio[name=gender]').is(':checked');


	function checkIt() { //유효성 검사
		var password = $("#password").val();
		var birth = $("#birth").val();
		var email = $("#email").val();
		var address = $("#address").val();
		

		if (!password) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		if (!name) {
			alert("이름을 입력해주세요.");
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
			console.log(authenticationnum+"유효성부분");
			return false;
			}
		
		if (!address) {
			alert("주소를 입력해주세요.");
			return false;
		}
	}


	
	function emailCheck1() {
		var em = $("#email").val();
		var url = 'mailauthentication.do?email=' + em;
		window.open(url, 'window팝업',
				'width=600, height=600, menubar=no, status=no, toolbar=no');
	}
	


	

	var ma_authentication = $("#authentication").val();
	function authentication_method() {
		$
				.ajax({
					type : "post",
					url : "authentication.do",
					dataType : "text"
				})
				.done(
						function(data) {				
							if ($("#authentication").val() == data){
							alert('인증이 완료되었습니다')
							document.getElementById("mail_authentication").style.display = "none";
							  btn = document.getElementById('authentication_mail');
							  btn.disabled = 'disabled';
								authenticationnum = 1;
							  console.log(authenticationnum+"이메일보내는부분");
							}
							else{
								  alert('인증번호가 다릅니다.')}
							 
						});
	}

	

	function mailcheck() {
		var emailadd = $("#email").val();
		$
				.ajax({
					type : "post",
					url : "send.do",
					data : "emailadd=" + emailadd,
					dataType : "text"
				})
				.done(
						function(data) {
							alert('입력하신 메일로 인증메일을 발송하였습니다.')
							document.getElementById("mail_authentication").style.display = "block";
						});
	}
</script>


<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.
							//document.getElementById("sample6_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample6_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode;
						document.getElementById("address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress")
								.focus();
					}
				}).open();
	}
</script>
<body>
	<form action="profileupdate.do" onsubmit="return checkIt()">
		ID : <input type="text" id="user_id" name="user_id" value="${SignDTO.user_id}" readonly="readonly"><br>
		PW : <input type="password" id="password" name="password" placeholder="변경할 비밀번호"><br>
		PW2:<input type="password" id="password2" name="password2" placeholder="비밀번호 확인"><br>
		<div name="pwcheck" id="pwcheck" style="display: none">비밀번호가 맞지 않습니다.</div>
		이름 : <input type="text" id="name" name="name" value="${SignDTO.name}" readonly="readonly"><br>
		생년월일<input type="text" id="birth" name="birth" value="${SignDTO.birth}" readonly="readonly"><br>
		이메일:<input type="email" id="email" name="email" value="${SignDTO.email}">
		<input type="button" name="authentication_mail" id="authentication_mail" value="인증메일 보내기" onclick="mailcheck()"><br>
		<div name="mail_authentication" id="mail_authentication" style="display: none">
			<input type="text" name="authentication" id="authentication" 
			placeholder="인증코드"> <input type="button" 
			value="인증코드 확인" onclick="authentication_method()">
		</div>
		휴대폰번호:<input type="text" id="phone"name="phone" value="${SignDTO.phone}">
		<br> 우편번호:<input type="text"
			id="sample6_postcode" placeholder="우편번호"> <input
			type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
		주소:<input type="text" id="address" name="address" placeholder="주소"><br>
		상세주소:<input type="text" id="sample6_detailAddress" placeholder="상세주소">
		<br>
		<input type="submit" value="회원정보변경">
	</form>
	
	


</body>
</html>
