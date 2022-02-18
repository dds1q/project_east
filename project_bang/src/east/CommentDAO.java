package east;

import java.util.List;

public interface CommentDAO {	
	
	public List<CommentVO> findAll( String no ) throws Exception;
	public int addComment( CommentVO vo ) throws Exception;
	public int delAllComment( String no ) throws Exception;
	public int delComment( String rno , String no ) throws Exception;
	public int modifyComment( String no , String rno, String content) throws Exception;
}
