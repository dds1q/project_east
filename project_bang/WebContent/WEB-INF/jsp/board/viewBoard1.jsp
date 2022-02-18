<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시물 조회</title>
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
					alert('실패');					
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
					alert('실패');					
				}					
			}
		} 
	};
	xhr.open("GET", "delLike.east?no=" + no , true );
	xhr.send( null );
}
<%--
$("#btnRecommend").click(function(){
    if(confirm("해당 글을 추천하시겠습니까?")){
        document.form1.action="recommend.do";
        document.form1.submit();
        
        alert("해당 글을 추천하였습니다.")
        
        }
    });
--%>

</script>
</head>
<body>
<span style="font-size:25px">
${username}님 환영합니다!! <a href="logout.east">[로그아웃]</a>
<div id="menu">
 <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</div>
<br/><br/>
[ 제목 : ${board.title} ]
<Q:choose>
	<Q:when test="${(empty like.username)}">
			<p id="btn"><img id="btn_push" onclick="push( ${board.no } )" src="heart_blank.png" width="20" height="20"/></p>
			<!--<a id="btn_push" href="javascript:push( ${board.no } )">[추천하기]</a>  -->
	</Q:when>
	<Q:otherwise>
			<p id="btn"><img id="btn_pull" onclick="pull( ${board.no } )" src="heart_full.png" width="20" height="20"/></p>
			
			<!--<a id="btn_pull" href="javascript:pull( ${board.no } )">[추천취소]</a> -->
	</Q:otherwise>	
</Q:choose>
<div id="count">${board.likeCount }</div>	
</span>
<br/> 
<br/> 
<label>작성자 : ${board.author}</label>	
<br/><br/>
<label> 업로드 파일 : 
<Q:choose>
	<Q:when test="${!(empty board.fsn)}">
		${board.ofn}
		<a href="down.east?ofn=${board.ofn}&fsn=${board.fsn}"><img src="down.svg"></a>
	</Q:when>
	<Q:otherwise>		
	파일이 없습니다.
	</Q:otherwise>
</Q:choose>
</label>	
<br/><br/>
<label>내용 : ${board.content}</label>
<br/><br/>	
<span style="font-size:25px">
[            댓글            ]
</span>	
<br/>
<Q:forEach items="${comment }" var="comment">
<ul>
<li>
	<div>
		<p>댓글 작성자: ${comment.author} || ${comment.regDate}
		<Q:if test="${username eq comment.author}">	
			<a href="delComment.east?rno=${comment.rno}&no=${board.no}">[댓글 삭제]</a>			
		</Q:if>	
		<Q:if test="${username eq comment.author}">
			<button id="ModButton" onclick="dis()">댓글 수정</button>
		</Q:if>
		</p>		
		<p>댓글 내용: ${comment.content}</p>
		<Q:if test="${username eq comment.author}">
		<div id="viewMod" style="display:none;">
			<form method="POST" action="modifyComment.east">
				<label>	수정할 내용</label><br/><br/>
				<textarea rows="5" cols="50" name="content">${comment.content }</textarea>
				<input type="hidden" name="no" value="${board.no }"/>
				<input type="hidden" name="rno" value="${comment.rno }"/>
				<button type="submit">댓글 수정</button>
			</form>
		</div>
		</Q:if>
	</div>
</li>
</ul>
</Q:forEach>
<br/><br/>
<form method="POST" action="addComment.east">
<label>	작성할 내용</label><br/><br/>
<textarea rows="5" cols="50" name="content"></textarea>
<input type="hidden" name="no" value="${board.no}"/>

<button type="submit">댓글 작성</button>
</form>
	<Q:if test="${username eq board.author}">	
		<a href="modifyPage.east?no=${board.no}">[수정하기]</a>	
	</Q:if>	
	<br/>
	<Q:if test="${username eq board.author}">	
		<a href="delBoard.east?no=${board.no}">[삭제하기]</a>	
	</Q:if>		
	<br/>
	<a href="board_list.east">[목록으로 가기]</a>	
</body>
</html>

<!-- 
insert into tbl_board( title , content , author ) values('텍스트제목1','텍스트 내용1','작성자1');
insert into tbl_board( title , content , author ) values('텍스트제목2','텍스트 내용2','작성자2');
insert into tbl_board( title , content , author ) values('텍스트제목3','텍스트 내용3','작성자3');

CREATE TABLE tbl_comment (
	rno INT NOT NULL AUTO_INCREMENT,
	no INT NOT NULL,
	author VARCHAR( 50 ) NOT NULL,
	content VARCHAR( 50 ) NOT NULL,
	regDate timestamp not null default NOW(),
	PRIMARY KEY( rno , no ),
	FOREIGN KEY( no ) REFERENCES tbl_board( no )	
);

INSERT INTO tbl_comment( no , author , content , regDate ) VALUES ( 4 , '댓글 작성자1' , '댓글 내용1', SYSDATE() );

 -->
