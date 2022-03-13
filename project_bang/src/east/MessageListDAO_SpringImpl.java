package east;

import org.springframework.jdbc.core.JdbcTemplate;

public class MessageListDAO_SpringImpl implements MessageListDAO {
	public JdbcTemplate jdbcTemplate = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
