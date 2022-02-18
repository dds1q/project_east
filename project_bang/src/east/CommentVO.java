package east;

import java.util.Date;

public class CommentVO {
	private Integer rno = null;
	private Integer no = null;
	private String author = null;
	private String content = null;
	private Date regDate = null;
	private String fsn = null;
	private String nick = null;
	public String getFsn() {
		return fsn;
	}
	public void setFsn(String fsn) {
		this.fsn = fsn;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Integer getRno() {
		return rno;
	}
	public void setRno(Integer rno) {
		this.rno = rno;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
/*
CREATE TABLE tbl_comment (
	rno INT NOT NULL AUTO_INCREMENT ,
	no INT NOT NULL,
	author VARCHAR( 50 ) NOT NULL,
	content VARCHAR( 50 ) NOT NULL,
	regDate timestamp not null default NOW(),
	PRIMARY KEY( rno , no ),
	FOREIGN KEY( no ) REFERENCES tbl_board( no )	
);


*/