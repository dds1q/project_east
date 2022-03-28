package east;

public class MessageListVO {
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}

	private Integer mno = null;
	private String user_me = null;
	public String getUser_me() {
		return user_me;
	}
	public void setUser_me(String user_me) {
		this.user_me = user_me;
	}
	public String getUser_other() {
		return user_other;
	}
	public void setUser_other(String user_other) {
		this.user_other = user_other;
	}

	private String user_other = null;


}

/*
create table tbl_messagelist(
mno int auto_increment primary key,
user_me varchar( 50 ) not null,
user_other varchar( 50 ) not null
);
*/