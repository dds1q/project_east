package east;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
}
/*
CREATE TABLE user_list(
	username VARCHAR( 50 ) NOT NULL PRIMARY KEY,
	password VARCHAR( 50 ) NOT NULL
);


*/