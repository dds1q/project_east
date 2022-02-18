<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script>
function push( no ) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if( xhr.readyState == 4 ) {
			if( xhr.status == 200 ) {
				var rt = xhr.responseText;
				var jo = window.eval( "("+ rt +")" );
				if( jo.success ){
					var dv = document.getElementById("push_12");
					dv.innerHTML = jo.count;
					alert('추천 성공');
				} else{

					alert( '추천 실패' );					
				}			
				
			}
		} 
	};
	xhr.open("GET", "push2.jsp?no=" + no , true );
	xhr.send( null );
}
</script>
</head>
<body>
	<%--
		글번호가 12번이고 추천수가 현재 100일때 추천하는 것
	--%>
	<div id="push_12">100</div>
	<a href="javascript:push( 12 )">12번글 추천하기</a>
</body>
</html>