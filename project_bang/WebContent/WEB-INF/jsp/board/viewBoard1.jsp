<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>�Խù� ��ȸ</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script>
function dis(){
    if($('#viewMod').css('display') == 'none'){
        $('#viewMod').show();
    }else {
        $('#viewMod').hide();
    }
}	

function push( no ) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if( xhr.readyState == 4 ) {
			if( xhr.status == 200 ) {
				var rt = xhr.responseText;
				var jo = window.eval( "("+ rt +")" );
				if( jo.success ){
					var count = document.getElementById("count");					
					count.innerHTML = jo.likeCount;
					var btn = document.getElementById("btn");
					btn.innerHTML ="<img id='btn_pull' onclick='pull( ${board.no } )' src='heart_full.png' width='20' height='20'/>";
					
				} else{
					alert('����');					
				}				
			}
		} 
	};
	xhr.open("GET", "addLike.east?no=" + no , true );
	xhr.send( null );
}
function pull( no ) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if( xhr.readyState == 4 ) {
			if( xhr.status == 200 ) {
				var rt = xhr.responseText;
				var jo = window.eval( "("+ rt +")" );
				if( jo.success ){
					var count = document.getElementById("count");					
					count.innerHTML = jo.likeCount;
					var btn = document.getElementById("btn");	
					btn.innerHTML ="<img id='btn_push' onclick='push( ${board.no } )' src='heart_blank.png' width='20' height='20'/>";
					
				} else{
					alert('����');					
				}					
			}
		} 
	};
	xhr.open("GET", "delLike.east?no=" + no , true );
	xhr.send( null );
}
<%--
$("#btnRecommend").click(function(){
    if(confirm("�ش� ���� ��õ�Ͻðڽ��ϱ�?")){
        document.form1.action="recommend.do";
        document.form1.submit();
        
        alert("�ش� ���� ��õ�Ͽ����ϴ�.")
        
        }
    });
--%>

</script>
</head>
<body>
<span style="font-size:25px">
${username}�� ȯ���մϴ�!! <a href="logout.east">[�α׾ƿ�]</a>
<div id="menu">
 <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</div>
<br/><br/>
[ ���� : ${board.title} ]
<Q:choose>
	<Q:when test="${(empty like.username)}">
			<p id="btn"><img id="btn_push" onclick="push( ${board.no } )" src="heart_blank.png" width="20" height="20"/></p>
			<!--<a id="btn_push" href="javascript:push( ${board.no } )">[��õ�ϱ�]</a>  -->
	</Q:when>
	<Q:otherwise>
			<p id="btn"><img id="btn_pull" onclick="pull( ${board.no } )" src="heart_full.png" width="20" height="20"/></p>
			
			<!--<a id="btn_pull" href="javascript:pull( ${board.no } )">[��õ���]</a> -->
	</Q:otherwise>	
</Q:choose>
<div id="count">${board.likeCount }</div>	
</span>
<br/> 
<br/> 
<label>�ۼ��� : ${board.author}</label>	
<br/><br/>
<label> ���ε� ���� : 
<Q:choose>
	<Q:when test="${!(empty board.fsn)}">
		${board.ofn}
		<a href="down.east?ofn=${board.ofn}&fsn=${board.fsn}"><img src="down.svg"></a>
	</Q:when>
	<Q:otherwise>		
	������ �����ϴ�.
	</Q:otherwise>
</Q:choose>
</label>	
<br/><br/>
<label>���� : ${board.content}</label>
<br/><br/>	
<span style="font-size:25px">
[            ���            ]
</span>	
<br/>
<Q:forEach items="${comment }" var="comment">
<ul>
<li>
	<div>
		<p>��� �ۼ���: ${comment.author} || ${comment.regDate}
		<Q:if test="${username eq comment.author}">	
			<a href="delComment.east?rno=${comment.rno}&no=${board.no}">[��� ����]</a>			
		</Q:if>	
		<Q:if test="${username eq comment.author}">
			<button id="ModButton" onclick="dis()">��� ����</button>
		</Q:if>
		</p>		
		<p>��� ����: ${comment.content}</p>
		<Q:if test="${username eq comment.author}">
		<div id="viewMod" style="display:none;">
			<form method="POST" action="modifyComment.east">
				<label>	������ ����</label><br/><br/>
				<textarea rows="5" cols="50" name="content">${comment.content }</textarea>
				<input type="hidden" name="no" value="${board.no }"/>
				<input type="hidden" name="rno" value="${comment.rno }"/>
				<button type="submit">��� ����</button>
			</form>
		</div>
		</Q:if>
	</div>
</li>
</ul>
</Q:forEach>
<br/><br/>
<form method="POST" action="addComment.east">
<label>	�ۼ��� ����</label><br/><br/>
<textarea rows="5" cols="50" name="content"></textarea>
<input type="hidden" name="no" value="${board.no}"/>

<button type="submit">��� �ۼ�</button>
</form>
	<Q:if test="${username eq board.author}">	
		<a href="modifyPage.east?no=${board.no}">[�����ϱ�]</a>	
	</Q:if>	
	<br/>
	<Q:if test="${username eq board.author}">	
		<a href="delBoard.east?no=${board.no}">[�����ϱ�]</a>	
	</Q:if>		
	<br/>
	<a href="board_list.east">[������� ����]</a>	
</body>
</html>

<!-- 
insert into tbl_board( title , content , author ) values('�ؽ�Ʈ����1','�ؽ�Ʈ ����1','�ۼ���1');
insert into tbl_board( title , content , author ) values('�ؽ�Ʈ����2','�ؽ�Ʈ ����2','�ۼ���2');
insert into tbl_board( title , content , author ) values('�ؽ�Ʈ����3','�ؽ�Ʈ ����3','�ۼ���3');

CREATE TABLE tbl_comment (
	rno INT NOT NULL AUTO_INCREMENT,
	no INT NOT NULL,
	author VARCHAR( 50 ) NOT NULL,
	content VARCHAR( 50 ) NOT NULL,
	regDate timestamp not null default NOW(),
	PRIMARY KEY( rno , no ),
	FOREIGN KEY( no ) REFERENCES tbl_board( no )	
);

INSERT INTO tbl_comment( no , author , content , regDate ) VALUES ( 4 , '��� �ۼ���1' , '��� ����1', SYSDATE() );

 -->
