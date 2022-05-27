package east;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class BoardDAO_SpringImpl implements BoardDAO{
	private JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<BoardVO> findAll() throws Exception {
		RowMapper<BoardVO> rowMapper = new RowMapper<BoardVO>() {
			@Override
			public BoardVO mapRow(ResultSet rs, int arg1) throws SQLException {
				BoardVO vo = new BoardVO();
				vo.setNo( rs.getInt("no") );
				vo.setTitle( rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setAuthor( rs.getString("author"));
				vo.setRegDate( rs.getDate("regDate"));
				vo.setFsn( rs.getString("fsn"));
				vo.setOfn( rs.getString("ofn"));
				vo.setViewcount( rs.getInt("viewcount"));
				vo.setLikeCount( rs.getInt("likeCount"));
				return vo;
			}
			
		};
		return jdbcTemplate.query("select * from tbl_board ORDER BY no DESC ", rowMapper);
	}
	
	@Override
	public int addBoard(BoardVO vo) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {				
				stmt.setString( 1 , vo.getTitle() );
				stmt.setString( 2 , vo.getContent() ) ;			
				stmt.setString( 3 , vo.getAuthor() ) ;	
				stmt.setString( 4 , vo.getOfn() );
				stmt.setString( 5 , vo.getFsn() );				
			}
			
		};
		int uc = jdbcTemplate.update("INSERT INTO tbl_board( title , content , author, ofn , fsn ) VALUES(  ? , ? , ? , ? , ? )" , pss );
		return uc;
	}
	@Override
	public BoardVO findBoard( String no ) throws Exception {		
 		// test
		RowMapper<BoardVO> rowMapper = new RowMapper<BoardVO>() {			
			
			@Override
			public BoardVO mapRow(ResultSet rs, int arg1) throws SQLException {
				BoardVO vo = new BoardVO();
				vo.setNo( rs.getInt("no") );
				vo.setTitle( rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setAuthor( rs.getString("author"));
				vo.setRegDate( rs.getDate("regDate"));
				vo.setViewcount( rs.getInt("viewcount"));
				vo.setOfn( rs.getString("ofn"));
				vo.setFsn( rs.getString("fsn"));
				vo.setLikeCount( rs.getInt("likeCount") );
				return vo;
			}			
		};			
		
		BoardVO vo = jdbcTemplate.queryForObject(
				"select * from tbl_board WHERE no="+ no , rowMapper);

		return vo;
	}


	@Override
	public int modifyBoard( BoardVO vo ) throws Exception {
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString( 1, vo.getTitle() );
				stmt.setString( 2 , vo.getContent() );	
				stmt.setString( 3 , vo.getOfn() );
				stmt.setString( 4 , vo.getFsn() );
							
			}
			
		};
		
		int uc = jdbcTemplate.update("UPDATE tbl_board SET title=? , content=? , ofn=? , fsn=? WHERE no=" + vo.getNo() , pss );
		
		return uc;

	}

	@Override
	public int delBoard(String no) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString( 1 , no ) ;				
			}
			
		};
		
		int uc = jdbcTemplate.update("DELETE FROM tbl_board WHERE no=?" , pss );
		
		return uc;

	}

	@Override
	public void updateviewCount(String no) throws Exception {		
		
		jdbcTemplate.update("UPDATE tbl_board SET viewCount = viewCount + 1 WHERE no=" + no);		
	}
	@Override
	public List<BoardVO> findBoard_like( String username ) throws Exception {
		RowMapper<BoardVO> rowMapper = new RowMapper<BoardVO>() {
			@Override
			public BoardVO mapRow(ResultSet rs, int arg1) throws SQLException {
				BoardVO vo = new BoardVO();
				vo.setNo( rs.getInt("no") );
				vo.setTitle( rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setAuthor( rs.getString("author"));
				vo.setRegDate( rs.getDate("regDate"));
				vo.setFsn( rs.getString("fsn"));
				vo.setOfn( rs.getString("ofn"));
				vo.setViewcount( rs.getInt("viewcount"));
				vo.setLikeCount( rs.getInt("likeCount"));
				return vo;
			}
			
		};
		return jdbcTemplate.query("select * from tbl_board where no in ( select no from tbl_like where username = '" + username + "' )ORDER BY no DESC", rowMapper);
	}
	@Override
	public int plusLikeCount(String no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int minusLikeCount(String no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
 

}
