package east;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class BangDAO_SpringImpl implements BangDAO {
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private JdbcTemplate jdbcTemplate = null;
	
	@Override
	public List<BangVO> findAll() throws Exception {
		RowMapper<BangVO> rowMapper = new RowMapper<BangVO>() {

			@Override
			public BangVO mapRow(ResultSet rs, int arg1) throws SQLException {
				BangVO vo = new BangVO();
				vo.setNo( rs.getInt("no") );
				vo.setContent(rs.getString("content"));
				vo.setAuthor( rs.getString("author"));
				vo.setOfn( rs.getString("ofn"));
				vo.setFsn( rs.getString("fsn"));
				return vo;
			}
			
		};
		return jdbcTemplate.query("select * from bang_list", rowMapper);
	}

	@Override
	public int add(BangVO vo) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString( 1 , vo.getContent() ) ;			
				stmt.setString( 2 , vo.getAuthor() ) ;	
				stmt.setString( 3 , vo.getOfn() ) ;	
				stmt.setString( 4 , vo.getFsn() ) ;	
			}
			
		};
		int uc = jdbcTemplate.update("INSERT INTO bang_list VALUES( default , ? , ? , ? , ? )" , pss );
		return uc;
	}
	
	@Override
	public int del( String no ) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString( 1 , no ) ;				
			}
			
		};
		
		int uc = jdbcTemplate.update("DELETE FROM bang_list WHERE no=?" , pss );
		
		return uc;
	}

}
