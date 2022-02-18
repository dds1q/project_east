<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
.font , table td , table th {
	font-family:'Nanum Gothic';
	font-size: 16px;
}
.font_sm {
	font-family:'Nanum Gothic';
	font-size: 12px;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<!-- onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="" -->
<body> 

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
<span style="font-size:25px">
${username}님 환영합니다!! <a href="logout.east">[로그아웃]</a>
<br/> 
<div id="menu">
 <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</div>
</span>
<div class="container">			
<div class="panel panel-success">	
<div class="panel heading">업로드 다운로드</div>
<div class="panel body">
    <table class="table table-bordered table-striped">
    <thead>
		<tr>
		<th>번호</th>
		<th>내용</th>
		<th>작성자</th>		
		<th>파일명</th>
		</tr>
		</thead>
		<tbody>
        <Q:forEach items="${list}" var="t">        
            <tr>
                <td>${t.no}</td>
                <td>${t.author}</td>
                <td>${t.content}</td>
                <td>
                <Q:choose>
						<Q:when test="${!(empty t.fsn)}">
							${t.ofn}
							<a href="down.east?ofn=${t.ofn}&fsn=${t.fsn}"><img src="down.svg"></a>
						</Q:when>
						<Q:otherwise>		
							파일이 없습니다.
						</Q:otherwise>
				</Q:choose>
                </td>           
            </tr>                
        </Q:forEach>
        </tbody>
    </table>
</div>
</div>
    <form method="POST" action="add.east" enctype="multipart/form-data">    	
		<div class="form-group">
			<label class="font" for="abcd">내용을 적어주세요</label>
			<textarea class="form-control font" id="abcd" name="content" rows="5"></textarea>
		</div>
		<div class="form-group">
		<input type="file" class="form-control font_sm" name="apple"/>
		</div>
		<div class="form-group has-success">
			<input class="btn btn-success" type="submit"/>
		</div>
    </form>        
</body>
</html>
<!-- 

 -->