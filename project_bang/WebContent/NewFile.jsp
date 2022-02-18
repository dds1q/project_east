<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE>
<html>
<head>
<style>
.main{
	color:"red"
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {   
	$("#zzz").click( function(){

		let id = $(this).attr('name');
   		
		alert( id );
		
		let id3 = $( '#zzz' + id ).attr('name');
		
		alert(id3);
	});
});
</script>
</head>
<body>
<input type="button" class="main" id="zzz" name="0" value="DD"/>
<input type="button" class="main" id="zzz0" name="1" value="DD"/>

</body>
</html>