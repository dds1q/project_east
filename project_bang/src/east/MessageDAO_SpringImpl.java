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
		int uc = jdbcTemplate.update("INSERT INTO tbl_message( from_user , to_user, message  ) "
				+ "VALUES(  ? , ? , ? )" , pss );
		return uc;
	}
}
