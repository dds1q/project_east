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
<head>
<style type="text/css">
/*
.modal-overlay {
    width: 100%;
    height: 100%;
    position: fixed;
    left: 50%;
    top: 50%;
    display: none;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background: rgba(255, 255, 255, 0.25);
    box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
    backdrop-filter: blur(1.5px);
    -webkit-backdrop-filter: blur(1.5px);
    border-radius: 10px;
    border: 1px solid rgba(255, 255, 255, 0.18);
    transform: translate(-50%, -50%);
}
.modal-window {
    background: rgba( 69, 139, 197, 0.70 );
    box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );
    backdrop-filter: blur( 13.5px );
    -webkit-backdrop-filter: blur( 13.5px );
    border-radius: 10px;
    border: 1px solid rgba( 255, 255, 255, 0.18 );
    width: 500px;
    height: 600px;
    position: relative;
    top: 0px;
    padding: 10px;
} 
.close-area {
    display: inline;
    float: right;
    padding-right: 10px;
    cursor: pointer;
    text-shadow: 1px 1px 2px gray;
    color: white;
}
.content {
    margin-top: 20px;
    padding: 0px 10px;
    text-shadow: 1px 1px 2px gray;
    color: white;
}
*/
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 200px;
    background-color: #f1f1f1;
    position:fixed;
    top:150px;
}
li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}
li a.active {
    background-color: #4CAF50;
    color: white;
}
li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
#header{
	border:2px solid rebeccapurple;	
	position:fixed;	
	width:90%;
}
#section{
	overflow:auto; 
	width:500x;
	height:70%;
	position:fixed;
	left:250px; 
	top:150px;	
}

#section::-webkit-scrollbar{
	width:8px;
}
#section::-webkit-scrollbar-thumb{	
	background-color:#1E90FF;
	border-radius:10px;
}

.main{
	border:6px solid;
	padding: 5px;
	border-radius:20px;
	width:90%;
	right:10%;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<script src="./bootstrapt/js/bootstrap.min.js"></script>
<script>
function load_pop( index ) {
	var the_height = document.getElementById('board' + index).contentWindow. document.body.scrollHeight;
	document.getElementById('board' + index).height = the_height;
	document.getElementById('board' + index).style.overflow = "hidden";	
 
	$("#board" + index ).contents().click(function(event){
	
		$("#modal" + index  ).modal("show");
	});

	/*
    document.getElementById("board"+ index ).contentWindow.document.body.onclick = function() {
    	document.getElementById('modal' + index ).style.display='flex';    	
        var id_open = document.getElementById('open'+index );
    	var id_modal = document.getElementById('board'+index );
    	$( id_open ).click(function(e){
    		e.preventDefault();
    		$(id_modal ).modal("show");
    	});	
    */	   
}

	
</script>
</head>
<body>
<div>
<div id="header">
<div style="font-size:10; text-align:right;" >
${username}님 환영합니다!! <a href="logout.east">[로그아웃]</a>
<br/> 
</div>
<Q:if test="${!(empty info.fsn) }">
<img src="image.jsp?fname=${info.fsn}" width="60" height="60"/>
</Q:if>
<br/>
닉네임 :  ${info.user_nick } ||  ${info.user_intro}
<br/>
</div>
</div>
<br/>
<div id="aside">
<%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</div>
<div id="section">
<Q:forEach items="${list}" var="board" varStatus="vs">

<!-- 클릭시 모달창 펼쳐지는 버튼 -->
<div class="main" data-toggle="modal" data-target="#modal${vs.index }">
<iframe id="board${vs.index }"
		name="dd"
		title="no example"			
		onload="load_pop( ${vs.index})"
		frameborder="0" 
		scrolling="no"
		style="overflow-x:hidden; overflow:auto; width:100%; left:100px;"						
		src="viewBoard.east?no=${board.no }">		
</iframe> 
</div>

<div class="modal fade" id="modal${vs.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
	<div class="modal-content">	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span></button>
	</div>
	<div class="modal-body">
		<iframe id="no"		
			width="550" height="550"
			style="border:0"
			src="viewBoard_full.east?no=${board.no }">		
		</iframe>	
	</div>
	</div>
	</div>
</div>
<br/>
</Q:forEach> 
</div> 		

</body>
</html>