<%@ page contentType="text/plain;charset=utf-8"
    pageEncoding="EUC-KR"
    import="org.json.*"%><%--
    
    String no = request.getParameter("no");
    System.out.println( no );
    
    String sql = "update xxx_T set push = push + 1 where no = " + no;
    int uc = executeUpdate( sql );
    if( uc != 1 ) {
    	JSONObject jo = new JSONObject();
    	jo.put("success",false);
    	out.print( jo );
    	
    	return; 
    }
    
    String sql2 = "select push from xxx_T where no = " + no;
    
    JSONObject jo = new JSONObject();
    jo.put("success",true);
    jo.put("count",101);
    
    out.print( jo );
--%>
