<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Noto Sans KR", sans-serif;
}
.wrap {
  width: 100%;  
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.1);
}
.login{
  height:1000px;
  width: 600px;
  background-color:#393E46;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
h2 {
  color: tomato;
  font-size: 2em;
}
.login_id {
  margin-top: 20px;
  width: 80%;
}
.login_id input {
  width: 100%;
  height: 50px;
  border-radius: 30px;
  margin-top: 10px;
  padding: 0px 20px;
  border: 1px solid lightgray;
  outline: none;
       display: flex;
  align-items: center;
  justify-content: center;
}
.login_pw {
  margin-top: 20px;
  width: 80%;
}
.login_pw input {
  width: 100%;
  height: 50px;
  border-radius: 30px;
  margin-top: 10px;
  padding: 0px 20px;
  border: 1px solid lightgray;
  outline: none;
       display: flex;
  align-items: center;
  justify-content: center;
}
.submit {
  margin-top: 50px;
  width: 80%;
}
.submit input {
  width: 100%;
  height: 50px;
  border: 0;
  outline: none;
  border-radius: 40px;
  background: linear-gradient(to left, rgb(255, 77, 46), rgb(255, 155, 47));
  color: white;
  font-size: 1.2em;
  letter-spacing: 2px;
}
h4{
	color:white;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<html>
<body><div class="wrap">
<form method="GET" name="form" class="login">	
	<h2> sign up </h2>
	<div class="login_id">
		<h4>ID</h4>
		<input id="username" type="text" name="username" placeholder="ID"/>
	</div>
	<div class="login_pw">
		<h4>password</h4>
		<input id="password" type="password" name="password" placeholder="PASSWORD"/>	
	</div>	
	<br/>
		<div class="login_id">
		<h4>그림에 보이는 문자를 정확하게 입력하세요</h4>
		<br/>		
		<img src="image.jsp?fname=${fname}"/>
		<input type="text" class="form-control input input-sm" style="width:200px;" name="captcha"/>
		<input type="hidden" name="key" value="${key}"/>
		</div>
	<div class="submit">
	<input type="submit" value="sign up" onclick="javascript: form.action='sign_up2.east';"/>
	</div>
</form>


</div>
<!-- 
	회원가입</br><br/>
	<form method="GET" action="sign_up2.east">
		<table>
		<tr>
			<td>ID :</td>
			<td><input type="text" name ="username"/></td>			
		</tr>
		<tr>		
			<td>password : </td>
			<td><input type="password" name="password"/></td>				
		</tr>
		</table>

		
		
		<br/>
		그림에 보이는 문자를 정확하게 입력하세요
		<br/>
		<img src="image.jsp?fname=${fname}"/>
		<input type="text" name="captcha"/>
		<input type="hidden" name="key" value="${key}"/>
		<input type="submit"/>
	</form>
	 -->
</body>
</html>