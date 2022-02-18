package east;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class FollowDAO_SpringImpl implements FollowDAO{
	JdbcTemplate jdbcTemplate = null;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public boolean findFollow(String username_me, String username_other) throws Exception {
		RowMapper<FollowVO> rowMapper = new RowMapper<FollowVO>() {					
		@Override
		public FollowVO mapRow(ResultSet rs, int arg1) throws SQLException {
			FollowVO vo = new FollowVO();
			vo.setFrom_user( rs.getString("from_user") );
			vo.setTo_user( rs.getString("to_user"));
			return vo;
			}			
		};		
		FollowVO vo = new FollowVO();
		try {
			vo = jdbcTemplate.queryForObject(
					"select * from tbl_follow WHERE from_user='"+ username_me + "' AND to_user ='" + username_other +"'" , rowMapper);
			if( vo != null ) {
				return true;
			} else {
				return false;
			}
			
		}	catch( EmptyResultDataAccessException e ) { return false;	}
		
	}
	@Override
	public List<FollowVO> findFollowing( String username ) throws Exception {
		
	RowMapper<FollowVO> rowMapper = new RowMapper<FollowVO>() {					
		@Override
		public FollowVO mapRow(ResultSet rs, int arg1) throws SQLException {
			FollowVO vo = new FollowVO();
			vo.setFrom_user( rs.getString("from_user") );
			vo.setTo_user( rs.getString("to_user"));
			return vo;
			}			
		};		
		return jdbcTemplate.query("select * from tbl_follow WHERE from_user='"+ username + "'", rowMapper);
		

	}

	@Override
	public List<FollowVO> findFollower(String username) throws Exception {
		RowMapper<FollowVO> rowMapper = new RowMapper<FollowVO>() {					
			@Override
			public FollowVO mapRow(ResultSet rs, int arg1) throws SQLException {
				FollowVO vo = new FollowVO();
				vo.setFrom_user( rs.getString("from_user") );
				vo.setTo_user( rs.getString("to_user"));
				return vo;
				}			
			};		
			
			return jdbcTemplate.query("select * from tbl_follow WHERE to_user='"+ username + "'", rowMapper);
			

		
	}

	@Override
	public int addFollow(FollowVO vo) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {				
				stmt.setString( 1 , vo.getFrom_user() );
				stmt.setString( 2 , vo.getTo_user() ) ;				
			}			
		};
		int uc = jdbcTemplate.update("INSERT INTO tbl_follow VALUES( default, ? , ? )" , pss );
		return uc;

	}

	@Override
	public int delFollow(FollowVO vo) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {				
				stmt.setString( 1 , vo.getFrom_user() );
				stmt.setString( 2 , vo.getTo_user() ) ;				
			}			
		};
		int uc = jdbcTemplate.update("DELETE FROM tbl_follow WHERE from_user=? AND to_user=?" , pss );
		return uc;
	}

	
}
