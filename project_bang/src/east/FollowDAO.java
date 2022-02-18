package east;

import java.util.List;

public interface FollowDAO {
	public boolean findFollow( String username_me , String username_other ) throws Exception;
	public int addFollow( FollowVO vo ) throws Exception;
	public int delFollow( FollowVO vo ) throws Exception;
	public List<FollowVO> findFollowing( String username ) throws Exception;
	public List<FollowVO> findFollower( String username ) throws Exception;
}
