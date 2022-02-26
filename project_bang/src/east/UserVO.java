package east;

public class UserVO {
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOfn() {
		return ofn;
	}
	public void setOfn(String ofn) {
		this.ofn = ofn;
	}
	public String getFsn() {
		return fsn;
	}
	public void setFsn(String fsn) {
		this.fsn = fsn;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getUser_intro() {
		return user_intro;
	}
	public void setUser_intro(String user_intro) {
		this.user_intro = user_intro;
	}
	private String fsn = null;
	private String user_nick = null;
	private String user_intro = null;
	private String username = null;
	private String password = null;
	private String ofn = null;
}
/*
alter table user_list ADD user_nick VARCHAR( 50 ) NULL AFTER password;
alter table user_list ADD user_intro VARCHAR( 50 ) NULL AFTER user_nick;
alter table user_list ADD ofn VARCHAR( 50 ) NULL AFTER user_intro;
alter table user_list ADD fsn VARCHAR( 50 ) NULL AFTER ofn;


*/