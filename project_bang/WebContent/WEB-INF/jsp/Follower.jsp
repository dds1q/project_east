<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="Q" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="./bootstrapt/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">

</script>
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-center row">
        <div class="col-md-6">
            <div class="bg-white text-center">            
                <div>
                    <h1>Follower</h1>
                </div>  
                <Q:forEach items="${ userinfo }" var="userinfo" varStatus="vs">
                    <div class="d-flex flex-row justify-content-between align-items-center" onclick="window.parent.location.href ='board_list_nick.east?nick=${userinfo.value}'">
                    <div class="d-flex flex-row align-items-center"><img class="rounded-circle" src="image.jsp?fname=${userinfo.key }" width="55">
                    <div class="d-flex flex-column align-items-start ml-2"><span class="font-weight-bold">${userinfo.value }</span></div>
                	</div>             
                   
                </div>					
				<br/>
				</Q:forEach>  
            </div>
        </div>
    </div>
</div>
</body>
</html>