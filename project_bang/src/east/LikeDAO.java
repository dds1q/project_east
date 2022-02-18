package east;


public interface LikeDAO {
	public int addLike(LikeVO vo) throws Exception ;
	public int delLike(LikeVO vo) throws Exception ;
	public LikeVO findLike( String no , String username ) throws Exception;
	public int LikeCount( String no ) throws Exception ;
	public boolean findLikeA( String no, String username ) throws Exception;
}
