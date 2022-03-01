<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시물 조회</title>
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
<style>
@import url(http://fonts.googleapis.com/css?family=Calibri:400,300,700);


.mt-100 {
    margin-top: 100px
}

.mb-100 {
    margin-bottom: 100px
}

.card {
    position: relative;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 0px solid transparent;
    border-radius: 0px
}

.card-body {
    -webkit-box-flex: 1;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 1.25rem
}

.card .card-title {
    position: relative;
    font-size:30px;
    font-weight: 750;
    margin-bottom: 15px;
}
.comment-widgets {
    position: relative;
    margin-bottom: 1px
}
.comment-widgets .comment-row {
    border-bottom: 1px solid transparent;
    padding: 7px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    margin: 5px 0
}

.p-2 {
    padding: 0.5rem !important
}

.comment-text {
    padding-left: 15px
}

.w-100 {
    width: 100% !important
}

.m-b-15 {
    margin-bottom: 15px
}

.btn-sm {
    padding: 0.25rem 0.5rem;
    font-size: 0.76563rem;
    line-height: 1.5;
    border-radius: 1px
}

.btn-cyan {
    color: #fff;
    background-color: #27a9e3;
    border-color: #27a9e3
}

.btn-cyan:hover {
    color: #fff;
    background-color: #1a93ca;
    border-color: #198bbe
}


.comment-widgets .comment-row:hover {
    background: rgba(0, 0, 0, 0.05)
}
body::-webkit-scrollbar{
	width:8px;
}
body::-webkit-scrollbar-thumb{	
	background-color:#1E90FF;
	border-radius:10px;
}
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
.card-footer{
position:fixed;
left:0px;
bottom:0px;
width: 100%;
text-align: center;
}

</style>
</head>
<body>
<div class="card gedf-card">
  <div class="card-header">
      <div class="d-flex justify-content-between align-items-center">
          <div class="d-flex justify-content-between align-items-center">
              <div class="mr-2">             
              	 <Q:choose>  
              	 	<Q:when test="${(empty info.fsn)}">                    
					 <img id="userimage" style="cursor:pointer;"onclick="click_board( '${board.author}' , '${username }');" src="anony.jpg" width="60" height="60"/>
					 </Q:when>
					 <Q:otherwise>
					 <img id="userimage" style="cursor:pointer;" onclick="click_board( '${board.author}' , '${username }');" src="image.jsp?fname=${info.fsn}" width="60" height="60"/>
					 </Q:otherwise>
				 </Q:choose>				 
			  </div> 
                    <div class="ml-2">
                    <div class="h5 m-0" id="nickname"><p onclick="click_board( '${board.author}' , '${username }');">${info.user_nick}</p></div>                                 
             </div>
             </div>
          </div>
    	 </div>
  		 <div class="card-body">
  		 <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i> ${board.regDate}</div>
  		 <h5 class="card-title" style="color:#1E90FF"> ${board.title} </h5>
  		 <p class="card-text">
			${board.content}
		</p>
<br/> 
<Q:if test="${!(empty board.fsn) }">
<img src="image.jsp?fname=${board.fsn}" width="100%" height="auto"/>
</Q:if>
<Q:choose>
	<Q:when test="${(empty like.username)}">
		<p id="btn" style="display:inline-block;"><img id="btn_push" onclick="push( ${board.no } );" src="heart_blank.png" width="20" height="20"/></p>
		<p id="count" style="display:inline-block;">${board.likeCount }</p>		
	</Q:when>
	<Q:otherwise>
		<p id="btn" style="display:inline-block;"> <img id="btn_pull"  onclick="pull( ${board.no } );" src="heart_full.png" width="20" height="20"/></p>
		<div id="count" style="display:inline-block;">${board.likeCount }</div>	
	</Q:otherwise>	
</Q:choose>

<div class="row d-flex justify-content-center">
    <div class="col-lg-6">
        <div class="card">            
            <div class="comment-widgets">
                <!-- Comment Row -->    
                <Q:forEach items="${comment }" var="comment">                       
                <div class="d-flex flex-row comment-row">     
                	<Q:forEach items="${users }"  var="users">  
                	<Q:if test="${ users.username eq comment.author }">
                	<Q:choose>
                	<Q:when test="${ !(empty users.fsn) }" >      	
                    <div class="p-2"><img src="image.jsp?fname=${users.fsn }" onclick="click_board( '${users.username}' , '${username }');" alt="user" width="50" class="rounded-circle"></div>
                    </Q:when>
                    <Q:otherwise>
                    <div class="p-2"><img src="anony.jpg" onclick="click_board( '${users.username}' , '${username }');" alt="user" width="50" class="rounded-circle"></div>
                    </Q:otherwise>
                    </Q:choose>
                    <div class="comment-text active w-100">
                        <h6 class="font-medium">${users.user_nick}</h6> <span class="m-b-15 d-block">${comment.content}</span>
                        <div class="comment-footer"> <span class="text-muted float-right">${comment.regDate}</span>
                       <Q:if test="${username eq comment.author}">
                        <button type="button" class="btn btn-danger btn-sm" onclick="location.href='delComment.east?rno=${comment.rno}&no=${board.no}';">Delete</button>
                    	</Q:if>	
                    </div>
                    </div>   
                    </Q:if>                 
                    </Q:forEach>
                </div> <!-- Comment Row -->                
                </Q:forEach>
            </div> <!-- Card -->
        </div>
    </div>
</div>
<!-- 
<Q:forEach items="${comment }" var="comment">

<ul>
<li>
	<div>
		<span><p onclick="click_board( '${comment.author}' , '${username }');">${comment.author}</p> || ${comment.regDate}</span>
		<Q:if test="${username eq comment.author}">				
			<a href="delComment.east?rno=${comment.rno}&no=${board.no}">[댓글 삭제]</a>	
			<button id="ModButton" onclick="dis()">댓글 수정</button>				
		</Q:if>	
	
		<p>${comment.content}</p>
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
</Q:forEach> -->
</div>
</div>
    
<div class="card-footer" >
    <div class="modal-meta-bottom x">
       <form method="POST" action="addComment.east">
       <textarea class="form-control" name="content" placeholder="Leave a commment.."></textarea>
       <input type="hidden" name="no" value="${board.no}"/>
       <button class="btn btn-info" type="submit" >submit</button>
       </form>
    </div> 
    </div>
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
