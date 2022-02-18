<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="./login.css">
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
  background-color:#393E46;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height:1000px;
  width: 600px;
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="wrap">
<form method="POST" name="form" class="login col-md-6" >	
	<img src="linkedin_profile_image.png" width="260" height="260">	
	<div class="login_id">
		<h4 style="color:white;">ID</h4>
		<input id="username" type="text" name="username" placeholder="ID"/>
	</div>
	<div class="login_pw">
		<h4 style="color:white;">password</h4>
		<input id="password" type="password" name="password" placeholder="PASSWORD"/>	
	</div>
	<div class="submit">
		<input type="submit" value="sign in" onclick="javascript: form.action='login2.east';"/>
	</div>		
	
	<div class="submit">
		<h4 style="font-size:6; color:white;">회원이 아닙니까?</h4>
		<input type="submit" onclick="javascript: form.action='sign_up.east';" value="sign up"/>	
	</div>
</form>
</div>

</body>
</html>