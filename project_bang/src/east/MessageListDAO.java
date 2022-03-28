package east;

import java.util.List;

public interface MessageListDAO {
	public List<MessageListVO> findAll( String username ) throws Exception;
}
