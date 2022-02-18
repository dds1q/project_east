<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setDateHeader("Expires", 0L); // Do not cache in proxy server

// 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.	
	
	if (session.getAttribute("username") == null) {    	
        response.sendRedirect("/project_bang/logout.east");        
    }
%>
<!DOCTYPE html>
<html>
<body>
<span style="font-size:25px">
${username}님 환영합니다!! <a href="logout.east">[로그아웃]</a>
<br/><br/>
[ 게시글 목록 ]
<br/> 
<br/> 
</span>
<div id="menu">
 <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</div>
<table>
	<thead>
		<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
		</tr>
	</thead>
 
	<tbody>
		<Q:forEach items="${list}" var="t">
		<tr>
  			<td>${t.no}</td>
  			<td>
  				<a href="viewBoard.east?no=${t.no}">${t.title}</a>  			
  			</td>
  			<td>${t.author}</td>
  			<td>${t.regDate}</td>
  			<td>${t.viewcount}</td>
  		</tr>		
		</Q:forEach>  		
	</tbody>	
</table>
<br/>
<a href="board_write.east">[게시글 작성]</a>

</body>
</html>