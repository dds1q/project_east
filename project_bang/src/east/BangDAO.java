package east;

import java.util.List;

public interface BangDAO {
	public List<BangVO> findAll() throws Exception;	
	public int add( BangVO vo ) throws Exception;
	public int del( String no ) throws Exception;
}

/*
CREATE TABLE bang_list(
	no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	content VARCHAR( 50 ) NULL,
	author VARCHAR( 50 ) NULL,
	ofn VARCHAR( 50 ) NULL,
	fsn VARCHAR( 50 ) NULL
);
*/