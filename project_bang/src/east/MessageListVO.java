package east;

public class MessageListVO {
	private Integer mno = null;
	private Integer mroom = null;
	private String user_from = null;
	private String user_to = null;
	private String send_time = null;
	private String read_time = null;
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	public Integer getMroom() {
		return mroom;
	}
	public void setMroom(Integer mroom) {
		this.mroom = mroom;
	}
	public String getUser_from() {
		return user_from;
	}
	public void setUser_from(String user_from) {
		this.user_from = user_from;
	}
	public String getUser_to() {
		return user_to;
	}
	public void setUser_to(String user_to) {
		this.user_to = user_to;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getRead_time() {
		return read_time;
	}
	public void setRead_time(String read_time) {
		this.read_time = read_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReadchk() {
		return readchk;
	}
	public void setReadchk(String readchk) {
		this.readchk = readchk;
	}
	private String content = null;
	private String readchk = null;
}

/*
create table tbl_messagelist(
mno int auto_increment primary key,
mroom int not null,
user_from varchar( 50 ) not null,
user_to varchar( 50 ) not null,
sent_time datetime not null,
read_time datetime not null,
content varchar( 1000 ) not null,
readchk int not null,
constraint user_from_fk foreign key( user_from ) references user_list( username ) on delete cascade on update cascade,
constraint user_to_fk foreign key( user_to ) references user_list( username ) on delete cascade on update cascade
);
*/