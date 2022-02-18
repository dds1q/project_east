package east;

import java.util.Date;

public class BoardVO {
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Integer getViewcount() {
		return viewcount;
	}
	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
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
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	private Integer no = null;
	private String title = null;
	private String content = null;
	private String author = null;
	private Date regDate = null;
	private Integer viewcount= null;
	private String ofn = null;
	private String fsn = null;
	private Integer likeCount = null;
	
}

/*
CREATE table tbl_board(
	no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title varchar(50) not null,
	content text not null,
	author varchar(50) not null,
	regDate timestamp not null default now(),
	viewCount int default 0,
	ofn VARCHAR( 50 ) NULL,
	fsn VARCHAR( 50 ), 
	likeCount INT default 0	
);
alter table tbl_board ADD ofn VARCHAR( 50 ) NULL AFTER viewCount;
alter table tbl_board ADD fsn VARCHAR( 50 ) NULL AFTER ofn;
alter table tbl_board ADD likeCount INT default 0 AFTER viewCount;

update tbl_board set likeCount = ( select count(*) from tbl_like where no=17 ) WHERE no=17;

*/