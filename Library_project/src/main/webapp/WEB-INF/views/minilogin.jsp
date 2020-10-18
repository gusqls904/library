<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- <div id="layer1" class="pop-layer" style="display: none; margin-top: -133px; margin-left: -210px;">
    <div class="pop-container">
        <div class="pop-conts">
        
            content //
            <p class="ctxt mb20">Thank you.<br>
            
            
			            
            </p>

            <div class="btn-r">
                <a href="#" class="btn-layerClose">Close</a>
            </div>
            // content
        </div>
    </div>
</div> -->
			<input type="text" id="user_id" name="user_id"><br>
			<input type="password" id="password" name="password"><br>
			<input type="button" value="로그인" onclick="checkidpw()">
			<input type="button" value="회원가입" onclick="window.open('sign.do','window팝업','width=500, height=500, menubar=no, status=no, toolbar=no');">
			<input type="button" value="pw찾기" onclick="window.open('idpwfind.do','window팝업','width=300, height=350, menubar=no, status=no, toolbar=no');">


</body>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script type="text/javascript">

//아이디 비밀번호 체크부분
function checkidpw(){
          var user_id = $("#user_id").val(); 
          var pw = $("#password").val();
          $.ajax({
              type : "POST",
              url : "login.do",
              data : "user_id=" + user_id + "&pw=" + pw,
              dataType : "html"
          }).done(function(data){     
                 if (data == 1) {
                     console.log(data);
                      alert('로그인 성공')
                      location.href="main2.do";
                  } else{
                	  alert('아이디나 비밀번호가 다릅니다.')
               }
          });
      }
      </script>
</html>