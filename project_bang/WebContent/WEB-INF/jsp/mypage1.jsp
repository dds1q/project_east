<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<body>
	<span style="font-size:25px">
		${username}님 환영합니다!! <a href="logout.east">[로그아웃]</a>
	</span>
	<br/> 
<div id="menu">
 <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</div>
</body>
</html>