<%@ page pageEncoding="euc-kr" 
	import="java.io.*, east.Util"%><%

	String fname = request.getParameter("fname");
	if( fname == null ) {
		fname = "1643003502116";
	}

	response.setContentType("image/jpeg");
	
	InputStream in2 = new FileInputStream( Util.upload() + fname + ".jpg" );
	OutputStream out2 = response.getOutputStream();

	byte[] buf = new byte[1024*8];
	int len = 0;
	
	while( ( len = in2.read( buf ) ) != -1 ) {
		out2.write( buf, 0, len );
		out2.flush();
	}

	out2.close();
	in2.close();

%>