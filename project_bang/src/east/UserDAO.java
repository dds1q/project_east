package east;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface UserDAO {
	public boolean findUser( String username , String password  ) throws Exception;
	public int addUser( String username, String password) throws Exception;
}
