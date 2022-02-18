package east;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class User_infoDAO_SpringImpl implements User_infoDAO {
	JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public User_infoVO findUser_info( String username ) throws Exception {			
		User_infoVO vo = new User_infoVO();	
		RowMapper<User_infoVO> rowMapper = new RowMapper<User_infoVO>() {			
				
			@Override
			public User_infoVO mapRow(ResultSet rs, int arg1) throws SQLException {
				User_infoVO vo = new User_infoVO();
				vo.setUsername( rs.getString("username") );		
				vo.setUser_nick( rs.getString("user_nick"));
				vo.setUser_intro( rs.getString("user_intro") );
				vo.setOfn( rs.getString("ofn"));
				vo.setFsn( rs.getString("fsn"));
				return vo;
			}		
		};			
		try {
			vo = jdbcTemplate.queryForObject(
					"select * from tbl_userinfo WHERE username='"+ username +"'" , rowMapper);
			return vo;
		}
		catch( Exception e ) { return vo ; }
	}
	
	@Override
	public int addUser_info(String username) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {				
				stmt.setString( 1 , username );			
				stmt.setString( 2 , username );
			}			
		};
		int uc = jdbcTemplate.update("INSERT INTO tbl_userinfo( username , user_nick ) VALUES( ? , ? )" , pss );
		return uc;
	}
	@Override
	public int modifyInfo(User_infoVO vo) {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString( 1, vo.getUser_nick() );
				stmt.setString( 2 , vo.getUser_intro() );	
				stmt.setString( 3 , vo.getOfn() );	
				stmt.setString( 4 , vo.getFsn() );								
			}
			
		};
		
		int uc = jdbcTemplate.update("UPDATE tbl_userinfo SET user_nick=? , user_intro=? , "
				+ "ofn=? ,fsn=? WHERE username='" + vo.getUsername() +"'" , pss );
		
		return uc;
	}
	@Override
	public String getUsername(String nick) throws Exception {
		RowMapper<User_infoVO> rowMapper = new RowMapper<User_infoVO>() {			
			
			@Override
			public User_infoVO mapRow(ResultSet rs, int arg1) throws SQLException {
				User_infoVO vo = new User_infoVO();
				vo.setUsername( rs.getString("username") );		
				vo.setUser_nick( rs.getString("user_nick"));
				vo.setUser_intro( rs.getString("user_intro") );
				vo.setOfn( rs.getString("ofn"));
				vo.setFsn( rs.getString("fsn"));
				return vo;
			}		
		};
		
		User_infoVO vo = jdbcTemplate.queryForObject(
				"select * from tbl_userinfo WHERE user_nick='"+ nick +"'" , rowMapper);
		return vo.getUsername();

	}
}
