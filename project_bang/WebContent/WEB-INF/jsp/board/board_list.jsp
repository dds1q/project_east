<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
.h7 {
    font-size: 0.8rem;
}
.gedf-wrapper {
    margin-top: 0.97rem;
}

@media (min-width: 601px) {
	body{
		overflow-y:hidden
	}
    .gedf-main {
        padding-left: 4rem;
        padding-right: 4rem;
    }
    .gedf-card {
        margin-bottom: 2.77rem;
     }
    
}
@media (max-width: 600px) {	
    #nav{
    	display:none;
    } 
    
}
/**Reset Bootstrap*/
.dropdown-toggle::after {
    content: none;
}
img {
    vertical-align: middle;
    border-style: none;
}
.section{
	overflow:auto; 
	width:100%;
	height:750px;
	left:250px; 
	top:150px;
	padding:20px;
}
.section::-webkit-scrollbar{
	width:8px;
}
.section::-webkit-scrollbar-thumb{	
	background-color:#1E90FF;
	border-radius:10px;
}
.profile-pic{
	border-radius:100%;
	border: 5px solid transparent;
	background-image: linear-gradient(#fff, #fff), 
    linear-gradient(to right, red 0%,  orange 100%);
	background-origin: border-box;
    background-clip: content-box, border-box;
	margin:10px;
}
.profile-pic_main{
	border-radius:100%;
	border: 3px solid transparent;
	background-image: linear-gradient(#fff, #fff), 
    linear-gradient(to right, red 0%,  orange 100%);
	background-origin: border-box;
    background-clip: content-box, border-box;
	margin:10px;
}
.loader { position: absolute; 
left: 50%; 
top: 50%; 
z-index: 1; 
width: 150px; 
height: 150px; 
margin: -75px 0 0 -75px; 
border: 16px solid #f3f3f3; 
border-radius: 50%; 
border-top: 
16px solid #3498db; 
width: 120px; 
height: 120px; 
-webkit-animation: spin 2s linear infinite; 
animation: spin 2s linear infinite; 
}
@-webkit-keyframes spin { 0% { -webkit-transform: rotate(0deg); } 100% { -webkit-transform: rotate(360deg); } } @keyframes spin { 0% { transform: rotate(0deg); } 100% { transform: rotate(360deg); } }
.col-md-7{
background-color:white; 
margin-top:30px; 
margin-left:10px; 
padding-top:10px
}
.card{
	box-shadow: 10px 10px 5px grey;
}

</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./bootstrapt/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script>
function load_pop( index , no  ) {
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
  	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if( xhr.readyState == 4 ) {
			if( xhr.status == 200 ) {
				var rt = xhr.responseText;
				var jo = window.eval( "("+ rt +")" );
				if( jo.success ){					
					document.getElementById("userimage" + index ).setAttribute("src", "image.jsp?fname="+jo.user_image) ;					
					document.getElementById("nickname" + index ).innerHTML=jo.user_nick;						
				} else{
					alert('실패');					
				}				
			}
		} 
	};	
	
	xhr.open("GET", "userimage.east?no=" + no , true );
	xhr.send( null );		
	$(".container").block();
}	
window.onload=function(){
	$(".loader").hide();	
}
function click_board( author , username ){
	if( author == username ){
		location.href ="board_list.east";
	} else {
		location.href="board_list_other.east?username=" + author ;
	}
}
function change_board(){
	location.href="board_list_like.east";
}
function search( target ){
	var nick = target.value;
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if( xhr.readyState == 4 ) {
			if( xhr.status == 200 ) {
				var rt = xhr.responseText;
				var jo = window.eval( "("+ rt +")" );
				if( jo.success ){
					var search_nick = document.getElementById("search_nick");
					for( var i = 0 ; i < jo.search.length ; i++){
						search_nick.value = jo.search[i].childNodes[0].nodeValue + "\n";				
					}				
				} else{
					alert('실패');					
				}				
			}
		} 
	};
	xhr.open("GET", "search_user.east?user_nick=" + nick , true );
	xhr.send( null );
}
</script>
</head>
<body>
<div class="loader">
</div>
<nav id="nav" style="background-color: #393E46; text-align:center; padding-top:20px; padding-bottom:20px" >
<img src="logo.jpg" onclick="location.href='board_list.east'" style="height:auto; width: 200px; cursor:pointer; margin-left:20%; display:inline-block;" >
<div style="color:white; display:inline-block; margin-right:10%; float:right;" >
<Q:choose>
<Q:when test="${ !(empty info.fsn) }">
<img class="profile-pic_main" src="image.jsp?fname=${info.fsn}" alt="profile" width="50px" height="50px" >
</Q:when>
<Q:otherwise>
<img class="profile-pic_main" src="anony.jpg" alt="profile" width="50px" height="50px" >
</Q:otherwise>
</Q:choose>
${username} <a href="logout.east">[logout]</a></div>
<div>
<form>
<input type="text" name="keyword" placeholder="serach nickname..." 
style="float:right;" onkeyup="search( this );"/>
<input type="text"  id="search_nick" value=""/>
</form>
</div>
</nav>
    <div class="container-fluid gedf-wrapper">
        <div class="row">
            <div class="col-md-4">            	
                <div class="card" style="margin-top:30px; margin-left:30px;">
                    <div class="card-body">
                    	<Q:choose>
                    	<Q:when test="${ empty info.fsn }">
                    	<img class="profile-pic" src="anony.jpg" alt="profile" width="150px" height="150px" >
                    	</Q:when>                    	
                    	<Q:otherwise>
                    	<img class="profile-pic" src="image.jsp?fname=${info.fsn}" alt="profile" width="150px" height="150px" >
                       	</Q:otherwise>
                       	</Q:choose>
                       	<br/><br/>
                        <div class="h5">${info.user_nick }</div>                       
                        <div class="h7">${info.user_intro }                            
                        </div>
                    <br/>                              
                    <div class="main" data-toggle="modal" data-target="#modal_profile">
						<button class="btn btn-primary btn-icon-text btn-edit-profile">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-edit btn-icon-prepend">
						    <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
						    <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
						</svg> Edit profile
						</button>
						<div class="modal fade" id="modal_profile" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document" >
						<div class="modal-content">							
							<span aria-hidden="true" class="close" data-dismiss="modal" aria-label="Close" style="font-size:30px;">&times;
							</span></button>								
							<iframe id="no"		
								width="470" height="700"
								style="border:0"
								src="modifyInfoPage.east">		
							</iframe>	
							</div>							
						</div>					
				 	</div>
					</div>  									
					<button class="btn btn-primary btn-icon-text btn-edit-profile" style="margin-top:5px;">
						 Message
					</button>                 
                   	</div>
                   	<!--  -->
                    <ul class="list-group list-group-flush">
                    	<div class="main" data-toggle="modal" data-target="#modal_follower">
                        <li class="list-group-item" >                        	
                            <div class="h6 text-muted" >Followers</div>                           
                            <div class="h5">${fn:length( follower ) }</div>                            
                        </li>			
							<div class="modal fade" id="modal_follower" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">							
									<span aria-hidden="true" class="close" data-dismiss="modal" aria-label="Close" style="font-size:30px;">&times;
									</span></button>								
									<iframe id="no"		
										width="470" height="700"
										style="border:0"
										src="followers.east?to_user=${ username }">		
										</iframe>	
										</div>							
								</div>					
							 </div>
						</div>
						<div class="main" data-toggle="modal" data-target="#modal_following">
                        <li class="list-group-item">
							<div class="h6 text-muted">Following</div>
                            <div class="h5">${fn:length( following ) }</div>
                        </li>		
							<div class="modal fade" id="modal_following" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document" >
								<div class="modal-content">							
									<span aria-hidden="true" class="close" data-dismiss="modal" aria-label="Close" style="font-size:30px;">&times;
									</span></button>								
										<iframe id="no"		
										width="470" height="700"
										style="border:0; align:center;"
										src="following.east?from_user=${ username }">		
										</iframe>	
										</div>							
									</div>					
							 	</div>						 	
						</div>				

                        <div class="main" data-toggle="modal" data-target="#modal_board_write">
							<li class="list-group-item">
 							<div class="h6 text-muted">게시글 작성</div>	 
 							</li>			
							<div class="modal fade" id="modal_board_write" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document"  role="document">
								<div class="modal-content">							
									<span aria-hidden="true" class="close" data-dismiss="modal" aria-label="Close" style="font-size:30px;">&times;
									</span></button>								
									<iframe id="no"		
										width="470" height="700"
										style="border:0"
										src="board_write.east">		
										</iframe>	
										</div>							
									</div>					
							 	</div>
						</div>
						<li class="list-group-item">
 							<div class="h6 text-muted" onclick="change_board();">관심글 보기</div>	 
 						</li> 	
                    </ul>
                </div>
            </div>            
           	<div class="col-md-7">
            <div class="container section" >      
                <Q:forEach items="${list}" var="board" varStatus="vs">
                <div class="card gedf-card">
                    <div class="card-header">
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="mr-2">                   
									 <img id="userimage${vs.index}" style="cursor:pointer;"onclick="click_board( '${board.author}' , '${username }');" src="anony.jpg" width="60" height="60"/>
                                </div>
                                <div class="ml-2">
                                    <div class="h5 m-0" id="nickname${vs.index}"></div>                                 
                                </div>                                
                            </div>
                            <div>                                
                            </div>
                        </div>
                    </div>
                <div class="card-body ">
              	<div class="main" data-toggle="modal" data-target="#modal${vs.index }">
                    <iframe id="board${vs.index}"
							title="no example"			
							onload="load_pop( ${vs.index} , ${board.no } )"
							frameborder="0" 
							scrolling="no"
							width="100%"
							style= "overflow-x:hidden; overflow-y:auto;"
							src="viewBoard.east?no=${board.no}">		
					</iframe> 					
					<div class="modal fade" id="modal${vs.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog"  role="document">
						<div class="modal-content">							
							<span aria-hidden="true" class="close" data-dismiss="modal" aria-label="Close" style="font-size:30px;">&times;
							</span></button>								
							<iframe id="no"		
								width="470" height="700"
								style="border:0"
								src="viewBoard_full.east?no=${board.no }">		
								</iframe>	
							</div>							
						</div>					
				 	</div>
				</div>                
                </div>                  
                <div class="card-footer">    
                <Q:if test="${username eq board.author}">                
                <div class="main" data-toggle="modal" data-target="#modal_board_modify${vs.index }">
                	<button type="button" class="btn btn-danger btn-sm" onclick="location.href='delBoard.east?no=${board.no}';">Delete</button>
                 	<button type="button" class="btn btn-danger btn-sm">Modify</button>					
					<div class="modal fade" id="modal_board_modify${vs.index }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document" style="max-width:60%; role="document">
						<div class="modal-content">							
							<span aria-hidden="true" class="close" data-dismiss="modal" aria-label="Close" style="font-size:30px;">&times;
							</span></button>								
							<iframe id="no"		
								width="470" height="700"
								style="border:0"
								src="modifyPage.east?no=${board.no}">		
							</iframe>	
							</div>							
						</div>					
				 	</div>
				</div>				
				</Q:if>				
                </div>
                </div>
                <br/>                
                </Q:forEach>

                <br/><br/><br/><br/><br/><br/>
                </div>                		
 			</div>
        </div>
    </div>



</body>
</html>