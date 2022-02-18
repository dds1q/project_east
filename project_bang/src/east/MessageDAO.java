package east;

import java.util.List;

public interface MessageDAO {
	public List<MessageVO> findAll() throws Exception;
	public int sendMessage( MessageVO vo) throws Exception;
}
