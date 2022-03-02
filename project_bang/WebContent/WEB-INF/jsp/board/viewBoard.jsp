<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시물 조회</title>
<style>
body {
            background-color: #eeeeee;
        }

        .h7 {
            font-size: 0.8rem;
        }

        .gedf-wrapper {
            margin-top: 0.97rem;
        }

        @media (min-width: 992px) {
            .gedf-main {
                padding-left: 4rem;
                padding-right: 4rem;
            }
            .gedf-card {
                margin-bottom: 2.77rem;
            }
        }

        /**Reset Bootstrap*/
        .dropdown-toggle::after {
            content: none;
        }
}
.card-title {
    position: relative;
    font-size:30px;
    font-weight: 750;
    margin-bottom: 15px;
}

</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./bootstrapt/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">
function dis(){
    if($('#viewMod').css('display') == 'none'){
        $('#viewMod').show();
    }else {
        $('#viewMod').hide();
    }
}	
function click_board( author , username ){
	if( author == username ){
		window.parent.location.href ="board_list.east";
	} else {
		window.parent.location.href="board_list_other.east?username=" + author ;
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
<!--  
<Q:if test="${!(empty info.fsn) }">
<img src="image.jsp?fname=${info.fsn}" width="60" height="60"/>
</Q:if>|| ${info.user_nick } <br/>
-->
<div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i> ${board.regDate}</div>
<h5 class="card-title" style="color:#1E90FF"> ${board.title} </h5>
<p class="card-text">
<Q:choose>
        <Q:when test="${fn:length(board.content) gt 26}">
        <Q:out value="${fn:substring(board.content, 0,130)}"></Q:out>...
        <span style="color:blue; font-size:8px;">[more]</span>
        </Q:when>
        <Q:otherwise>
        <Q:out value="${board.content}">
        </Q:out></Q:otherwise>
</Q:choose>
</p>
<!--
<Q:choose>
	<Q:when test="${(empty like.username)}">
			<div id="count"><img src="heart_blank.png" width="20" height="20"/>${board.likeCount }</div>	
				</Q:when>
	<Q:otherwise>
			<div id="count"><img id="btn_pull" src="heart_full.png" width="20" height="20"/>${board.likeCount }</div>		

	</Q:otherwise>		
</Q:choose>
<br/> 
<br/> 
 -->
<Q:if test="${!(empty board.fsn) }">
<img id="image" src="image.jsp?fname=${board.fsn}" max-width="100%" height="auto" style="display: block; margin: 0 auto;"/>
</Q:if>

<!--
<span style="font-size:25px">
[            댓글            ]
</span>	
<br/>
<Q:forEach items="${comment }" var="comment">
<ul>
<li>
	<div>
		<span>댓글 작성자: <p onclick="click_board( '${comment.author}' , '${username }');">${comment.author}</p> || ${comment.regDate}</span>
		<Q:if test="${username eq comment.author}">				
			<a href="delComment.east?rno=${comment.rno}&no=${board.no}">[댓글 삭제]</a>	
			<button id="ModButton" onclick="dis()">댓글 수정</button>				
		</Q:if>	
	
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
 -->
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
