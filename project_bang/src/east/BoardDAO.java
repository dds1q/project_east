package east;

import java.util.List;

public interface BoardDAO {
	public List<BoardVO> findAll() throws Exception;	
	public int addBoard( BoardVO vo ) throws Exception;
	public BoardVO findBoard( String no ) throws Exception;
	public int modifyBoard( BoardVO vo ) throws Exception;
	public int delBoard( String no ) throws Exception;
	public void updateviewCount( String no ) throws Exception;
	public int plusLikeCount( String no ) throws Exception;
	public int minusLikeCount( String no ) throws Exception;
}

//update 2 25ddddd