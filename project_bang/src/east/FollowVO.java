package east;

public class FollowVO {
	private Integer fno = null;
	private String from_user = null;
	private String to_user = null;
	public Integer getFno() {
		return fno;
	}
	public void setFno(Integer fno) {
		this.fno = fno;
	}
	public String getFrom_user() {
		return from_user;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public String getTo_user() {
		return to_user;
	}
	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}
}



/*
CREATE table tbl_follow(
	fno int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	from_user varchar(50) not null,
	to_user varchar(50) not null
);

INSERT INTO tbl_follow VALUES( default, 'apple' , 'admin' );
*/