package east;


public interface User_infoDAO {
	public User_infoVO findUser_info( String username ) throws Exception; 	
	public int addUser_info( String username ) throws Exception;
	public int modifyInfo(User_infoVO vo);
	public String getUsername( String nick ) throws Exception;
}
