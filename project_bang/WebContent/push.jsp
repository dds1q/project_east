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
					alert('��õ ����');
				} else{

					alert( '��õ ����' );					
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
		�۹�ȣ�� 12���̰� ��õ���� ���� 100�϶� ��õ�ϴ� ��
	--%>
	<div id="push_12">100</div>
	<a href="javascript:push( 12 )">12���� ��õ�ϱ�</a>
</body>
</html>