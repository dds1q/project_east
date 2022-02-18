package east;

public class User_infoVO {
	private Integer ino = null;
	private String username = null;
	private String ofn = null;
	private String fsn = null;
	private String user_nick = null;
	private String user_intro = null;
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
	public Integer getIno() {
		return ino;
	}
	public void setIno(Integer ino) {
		this.ino = ino;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
}


/*
CREATE TABLE tbl_userinfo (
	ino INT AUTO_INCREMENT PRIMARY KEY,
	username varchar( 50 ) NOT NULL,
	user_image varchar( 50 ) NULL,
	user_nick varchar( 50 ) NULL,
	user_intro varchar( 100 ) NULL,	
	
	constraint tbl_userinfo_username_fk foreign key( username ) references user_list( username ) on delete cascade on update cascade
);
INSERT INTO tbl_userinfo( username , user_nick , user_intro ) VALUES( 'apple' , '애플' , '안녕하세요 애플입니다' );  

alter table tbl_userinfo ADD ofn VARCHAR( 50 ) NULL AFTER user_intro;
alter table tbl_userinfo ADD fsn VARCHAR( 50 ) NULL AFTER ofn;
alter table tbl_userinfo drop user_image;

*/