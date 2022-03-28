<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>
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
	height:800px;
	left:250px; 
	top:150px;	
}
.section::-webkit-scrollbar{
	width:8px;
}
.section::-webkit-scrollbar-thumb{	
	background-color:#1E90FF;
	border-radius:10px;
}
.profile-pic{
	border-radius:40%;
}
img{ max-width:100%;}

.incoming_msg_img {
  display: inline-block;
  width: 6%;
}
.received_msg {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
 }
 .received_withd_msg p {
  background: #ebebeb none repeat scroll 0 0;
  border-radius: 3px;
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 12px;
  margin: 8px 0 0;
}
.received_withd_msg { width: 57%;}
.mesgs {
  float: left;
  padding: 30px 15px 0 25px;
  width: 60%;
}

 .sent_msg p {
  background: #05728f none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  width:100%;
}
.outgoing_msg{ overflow:hidden; margin:26px 0 26px;}
.sent_msg {
  float: right;
  width: 46%;
}
.input_msg_write input {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  color: #4c4c4c;
  font-size: 15px;
  min-height: 48px;
  width: 100%;
}

.type_msg {border-top: 1px solid #c4c4c4;position: relative;}
.msg_send_btn {
  background: #05728f none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 33px;
  position: absolute;
  right: 0;
  top: 11px;
  width: 33px;
}
.messaging { padding: 0 0 50px 0;}
.msg_history {
  height: 516px;
  overflow-y: auto;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./bootstrapt/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="card gedf-card">
	<div class="card-header">
		<div class="d-flex justify-content-between align-items-center">
			<div class="d-flex justify-content-between align-items-center">
			<div class="mr-2">                                   
				<img src="image.jsp?fname=${info.fsn}" width="60" height="60"/>
				</div>
                     <div class="ml-2">
                <div class="h5 m-0">${ info.user_nick }</div>                                 
            </div>                                
        </div>
        <div>                                
        </div>
    </div>
</div>
<div class="card-body">

<Q:forEach items="${ message }" var="message">
<Q:choose>
	<Q:when test="${ message.from_user eq info.username && message.to_user eq username}">
         <div class="incoming_msg">              
              <div class="received_msg">
                <div class="received_withd_msg">
                  <p> ${message.message }</p>
                  <span class="time_date"> ${message.regDate}</span></div>
              </div>
            </div>
     </Q:when>
     <Q:when test="${ message.from_user eq username && message.to_user eq info.username}">
            <div class="outgoing_msg">
              <div class="sent_msg">
                <p>${ message.message } </p>
                <span class="time_date"> ${message.regDate}</span> </div>
            </div>
      </Q:when>            
</Q:choose>
</Q:forEach>
</div>
</div>
                
<div class="card-footer">    
    <div class="modal-meta-bottom">
       <form method="POST" action="sendMessage.east" enctype="multipart/form-data">
       <textarea class="form-control" name="message" placeholder="Leave a Message.."></textarea>
       <input type="hidden"name="to_user" value="${info.username}"/>
       <button class="button" type="submit">¿€º∫</button>
       </form>
    </div> 				
</div>
</div>
</body>
</html>