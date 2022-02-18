package east;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class CommentDAO_SpringImpl implements CommentDAO {


	JdbcTemplate jdbcTemplate = null;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CommentVO> findAll( String no ) throws Exception {
		RowMapper<CommentVO> rowMapper = new RowMapper<CommentVO>() {

			@Override
			public CommentVO mapRow(ResultSet rs, int arg1) throws SQLException {
				CommentVO vo = new CommentVO();
				vo.setRno( rs.getInt("rno") );
				vo.setNo( rs.getInt("no") );
				vo.setAuthor( rs.getString("author"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate( rs.getDate("regDate"));
				vo.setFsn( rs.getString("fsn"));
				vo.setNick( rs.getString("nick"));
				return vo;
			}
			
		};
		return jdbcTemplate.query("select * from tbl_comment WHERE no=" + no , rowMapper);

	}

	@Override
	public int addComment(CommentVO vo) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
								
				stmt.setInt( 1 , vo.getNo() );
				stmt.setString( 2 , vo.getContent() ) ;			
				stmt.setString( 3 , vo.getAuthor() ) ;	
				stmt.setString( 4 , vo.getFsn() );
				stmt.setString( 5 , vo.getNick());
			}
			
		};
		int uc = jdbcTemplate.update("INSERT INTO tbl_comment( no , content , author ,fsn , nick ) VALUES(  ? , ? , ? , ? ,? )" , pss );
		return uc;
	}

	@Override
	public int delAllComment(String no) throws Exception {
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString( 1 , no ) ;				
			}
			
		};
		
		int uc = jdbcTemplate.update("DELETE FROM tbl_comment WHERE no=?" , pss );
		return uc;

	}

	@Override
	public int delComment( String rno , String no) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString( 1 , rno ) ;		
				stmt.setString( 2 , no ) ;
			}			
		};
		
		int uc = jdbcTemplate.update("DELETE FROM tbl_comment WHERE rno=? AND no=?" , pss );
		return uc;

	}

	@Override
	public int modifyComment(String no, String rno, String content) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString( 1 , content );	
				stmt.setString( 2, rno );
				stmt.setString( 3, no );							
			}
			
		};		
		int uc = jdbcTemplate.update("UPDATE tbl_comment SET content=? WHERE rno=? AND no=?" , pss );		
		return uc;
	}

}
