package east;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MessageListDAO_SpringImpl implements MessageListDAO {
	public JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public List<MessageListVO> findAll( String username) throws Exception {
		RowMapper<MessageListVO> rowMapper = new RowMapper<MessageListVO>() {
			@Override
			public MessageListVO mapRow(ResultSet rs, int arg1) throws SQLException {
				MessageListVO vo = new MessageListVO();
				vo.setMno( rs.getInt("mno") );
				vo.setUser_me( rs.getString("user_me"));
				vo.setUser_other(rs.getString("user_other"));
				return vo;
			}			
		};
		return jdbcTemplate.query("select * from tbl_messagelist where user_me ='" + username +"'" , rowMapper);
	}

}
