package east;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class LikeDAO_SpringImpl implements LikeDAO{
	JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	
	@Override
	public int addLike(LikeVO vo) throws Exception {		
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setInt( 1 , vo.getNo() ) ;			
				stmt.setString( 2 , vo.getUsername() ) ;
			}
			
		};
		int uc = jdbcTemplate.update("INSERT INTO tbl_like VALUES( default, ? , ? )" , pss );
		return uc;
	}



	@Override
	public int delLike( LikeVO vo ) throws Exception {		
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setInt( 1 , vo.getNo() ) ;	
				stmt.setString( 2 , vo.getUsername() ); 
			}
			
		};
		
		int uc = jdbcTemplate.update("DELETE FROM tbl_like WHERE no=? AND username=?" , pss );
		
		return uc;
	}
	@Override
	public LikeVO findLike(String no, String username) throws Exception {
		RowMapper<LikeVO> rowMapper = new RowMapper<LikeVO>() {			
			
			@Override
			public LikeVO mapRow(ResultSet rs, int arg1) throws SQLException {
				LikeVO vo = new LikeVO();
				vo.setNo( rs.getInt("no") );
				vo.setUsername( rs.getString("username"));
				return vo;
			}			
		};		
		LikeVO vo = new LikeVO();
		try {
			vo = jdbcTemplate.queryForObject(
					"select * from tbl_like WHERE no="+ no + " AND username ='" + username +"'" , rowMapper);
			
		}	catch( EmptyResultDataAccessException e ) { }

		return vo;

	}
	@Override
	public int LikeCount(String no) throws Exception {		
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {				
				stmt.setString( 1 , no ); 
				stmt.setString( 2 , no ); 
			}
			
		};		
		int uc = jdbcTemplate.update("update tbl_board set likeCount = ( select count(*) from tbl_like where no=? ) WHERE no=?" , pss);
		
		return uc;
	}
	@Override
	public boolean findLikeA(String no, String username) throws Exception {
		RowMapper<LikeVO> rowMapper = new RowMapper<LikeVO>() {			
			
			@Override
			public LikeVO mapRow(ResultSet rs, int arg1) throws SQLException {
				LikeVO vo = new LikeVO();
				vo.setNo( rs.getInt("no") );
				vo.setUsername( rs.getString("username"));
				return vo;
			}			
		};		
		LikeVO vo = new LikeVO();
		try {
			vo = jdbcTemplate.queryForObject(
					"select * from tbl_like WHERE no="+ no + " AND username ='" + username +"'" , rowMapper);
			if( vo != null ) {
				return true;
			} else {
				return false;
			}
			
		}catch( EmptyResultDataAccessException e ) { return false;	}
		
	}
}
