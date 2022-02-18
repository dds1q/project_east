<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setDateHeader("Expires", 0L); // Do not cache in proxy server

// ������ ������ ���°��, �ش��������� �� �� ���� ��.	
	
	if (session.getAttribute("username") == null) {    	
        response.sendRedirect("/project_bang/logout.east");        
    }
%>
<!DOCTYPE html>
<html>
<body>
<span style="font-size:25px">
${username}�� ȯ���մϴ�!! <a href="logout.east">[�α׾ƿ�]</a>
<br/><br/>
[ �Խñ� ��� ]
<br/> 
<br/> 
</span>
<div id="menu">
 <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</div>
<table>
	<thead>
		<tr>
		<th>��ȣ</th>
		<th>����</th>
		<th>�ۼ���</th>
		<th>�ۼ���</th>
		<th>��ȸ��</th>
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
<a href="board_write.east">[�Խñ� �ۼ�]</a>

</body>
</html>