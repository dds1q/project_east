package east;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class UserDAO_SpringImpl implements UserDAO {
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private JdbcTemplate jdbcTemplate = null;
	
	// 가입할 때 중복체크하기위해 유저정보를 불러옴
	@Override
	public boolean findUser(String username , String password ) throws Exception {
		boolean t = false;
 
		try {			
			RowMapper<UserVO> rowMapper = new RowMapper<UserVO>() {			
				
				@Override
				public UserVO mapRow(ResultSet rs, int arg1) throws SQLException {
					UserVO vo = new UserVO();				
					vo.setUsername(rs.getString("username"));
					vo.setPassword( rs.getString("password"));
					return vo;
				}			
			};
			
			
				UserVO vo = jdbcTemplate.queryForObject(
						"select * from user_list WHERE username='"+username + "' AND password='"+password
						+ "'",	rowMapper);
				if ( vo != null ) {
					t = true;							
				}	
		}
		catch( Exception e ) { e.toString(); }
		return t;
	}

	// 가입 후 유저정보 등록
	@Override
	public int addUser(String username, String password) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, username);
				stmt.setString(2, password);
			}
			
		};
		int uc = jdbcTemplate.update("INSERT INTO user_list VALUES( ? , ? )" , pss );
		return uc;		
	}
	
	// 특정 유저 정보 불러오기
	@Override
	public UserVO User_info(String username) throws Exception {

		RowMapper<UserVO> rowMapper = new RowMapper<UserVO>() {			
			
			@Override
			public UserVO mapRow(ResultSet rs, int arg1) throws SQLException {
				UserVO vo = new UserVO();				
				vo.setUsername( rs.getString("username"));				
				vo.setUser_nick( rs.getString("user_nick"));
				vo.setUser_intro( rs.getString("user_intro"));							
				vo.setFsn( rs.getString("fsn"));				
				return vo;
			}			
		};			
		
		UserVO vo = jdbcTemplate.queryForObject(
				"select * from user_list WHERE username='"+ username + "'", rowMapper);

		return vo;

	}

	// 유저 정보 수정하기
	@Override
	public int modifyInfo(UserVO vo) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString( 1, vo.getUser_nick() );
				stmt.setString( 2 , vo.getUser_intro() );	
				stmt.setString( 3 , vo.getOfn() );	
				stmt.setString( 4 , vo.getFsn() );								
			}
			
		};
		
		int uc = jdbcTemplate.update("UPDATE user_list SET user_nick=? , user_intro=? , "
				+ "ofn=? ,fsn=? WHERE username='" + vo.getUsername() +"'" , pss );
		
		return uc;
	}
}
/*
CREATE TABLE user_list(
	username VARCHAR( 50 ) NOT NULL PRIMARY KEY,
	password VARCHAR( 50 ) NOT NULL
);


*/