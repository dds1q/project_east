package east;

public class LikeVO {
	private Integer lno = null;
	private Integer no = null;
	private String username = null;
	public Integer getLno() {
		return lno;
	}
	public void setLno(Integer lno) {
		this.lno = lno;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}

/*


CREATE TABLE tbl_like(
	lno INT AUTO_INCREMENT PRIMARY KEY,
	no INT NOT NULL,
	username varchar( 50 ) NOT NULL,
	constraint tbl_like_no_fk foreign key( no ) references tbl_board( no ) on delete cascade,
	constraint tbl_like_username_fk foreign key( username ) references user_list( username ) on delete cascade on update cascade
);

*/