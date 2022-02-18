package east;

import java.util.Date;

public class MessageVO {
	private Integer mno = null;
	private String from_user = null;
	private String to_user = null;
	private String message = null;
	private Date regDate = null;
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}

/*
CREATE TABLE tbl_message (
mno int AUTO_INCREMENT PRIMARY KEY,
from_user VARCHAR( 50 ) NOT NULL,
to_user VARCHAR( 50 ) NOT NULL,
message VARCHAR( 200 ) NOT NULL,
regDate timestamp NOT NULL default now(),

constraint tbl_message_from_user_fk foreign key( from_user ) references user_list( username ) on delete cascade on update cascade,
constraint tbl_message_to_user_fk foreign key( to_user ) references user_list( username ) on delete cascade on update cascade
);
*/