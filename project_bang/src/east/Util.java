package east;

public class Util {
	public static String upload() {
		String t = System.getProperty("os.name");		
		
		// tomcat 서버내에 저장할 경우 경로
		String upload = "/pukyung15/upload/";
		
		// 데스크탑 내에 저장할 때
		if( t.indexOf("indows") != -1 ) {
			upload = "C:\\upload\\";
		}
		return upload;
	}
	
	public static boolean rectInside( double[][] rect_out, double[][] rect_in ) {	
		boolean inside = false ;
		
		if( (rect_out[0][0] < rect_in[0][0]) & (rect_out[0][1]< rect_in[0][1])  ) {
			if((rect_out[1][0] > rect_in[1][0]) & (rect_out[1][1]< rect_in[1][1])  ) {
				if( (rect_out[2][0] > rect_in[2][0]) & (rect_out[2][1] > rect_in[2][1]) ){
					if( (rect_out[3][0] < rect_in[3][0]) & (rect_out[3][1] > rect_in[3][1]) ){
						inside = true;
					}
				}
			}
		}
		return inside;
	}
}
