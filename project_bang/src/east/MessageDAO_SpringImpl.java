package east;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class MessageDAO_SpringImpl implements MessageDAO{
	JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<MessageVO> findAll() throws Exception {
		RowMapper<MessageVO> rowMapper = new RowMapper<MessageVO>() {
			@Override
			public MessageVO mapRow(ResultSet rs, int arg1) throws SQLException {
				MessageVO vo = new MessageVO();
				vo.setFrom_user( rs.getString("from_user"));
				vo.setTo_user( rs.getString("to_user"));
				vo.setMessage( rs.getString("message"));
				vo.setRegDate( rs.getDate("regDate"));
				return vo;
			}
			
		};
		return jdbcTemplate.query("select * from tbl_message ORDER BY regDate ASC", rowMapper);
	}
	
	@Override
	public int sendMessage(MessageVO vo) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {				
				stmt.setString( 1 , vo.getFrom_user() );
				stmt.setString( 2 , vo.getTo_user() ) ;			
				stmt.setString( 3 , vo.getMessage() ) ;				
			}
			
		};
		int uc = jdbcTemplate.update("INSERT INTO tbl_message( from_user , to_user , message  ) "
				+ "VALUES(  ? , ? , ? )" , pss );
		return uc;
	}
	@Override
	public boolean Messageroom(String me, String other) throws Exception {
		boolean t = false;		 
		try {			
			RowMapper<MessageVO> rowMapper = new RowMapper<MessageVO>() {					
				@Override
				public MessageVO mapRow(ResultSet rs, int arg1) throws SQLException {
					MessageVO vo = new MessageVO();				
					vo.setFrom_user(rs.getString("from_user"));
					vo.setTo_user( rs.getString("to_user"));
					return vo;
				}			
			};		
			MessageVO vo1 = jdbcTemplate.queryForObject(
						"select * from tbl_message where from_user='"+ me + "' AND to_user='"+other
						+ "'",	rowMapper);
			MessageVO vo2 = jdbcTemplate.queryForObject(
					"select * from tbl_message where from_user='"+ other + "' AND to_user='"+me
					+ "'",	rowMapper);
			if ( vo1 == null & vo2 == null ) {
				t = true;							
			}	
		}
		catch( Exception e ) { e.toString(); }
		return t;
	}
	@Override
	public int addMessageroom(String me, String other) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {								
				stmt.setString( 1 , me );
				stmt.setString( 2 , other ) ;						
			}
			
		};
		int uc = jdbcTemplate.update("INSERT INTO tbl_messagelist( user_me , user_other ) VALUES(  ? , ?   )" , pss );
		return uc;
	}	
}
