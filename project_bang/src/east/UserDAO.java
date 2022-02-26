package east;

public interface UserDAO {
	public boolean findUser( String username , String password  ) throws Exception;
	public int addUser( String username, String password) throws Exception;
	public UserVO User_info( String username ) throws Exception;
	public int modifyInfo(UserVO vo) throws Exception;
}
