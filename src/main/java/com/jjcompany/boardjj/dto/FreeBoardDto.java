package com.jjcompany.boardjj.dto;

import java.sql.Timestamp;

public class FreeBoardDto {
	private int fnum; // 게시글 번호
	private String fid; //게시자 id
	private String fname;
	private String ftitle;
	private String fcontent;
	private String hit;
	private Timestamp fdate;
	public FreeBoardDto(int fnum, String fid, String fname, String ftitle, String fcontent, String hit,
			Timestamp fdate) {
		super();
		this.fnum = fnum;
		this.fid = fid;
		this.fname = fname;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
		this.hit = hit;
		this.fdate = fdate;
	}
	public FreeBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFtitle() {
		return ftitle;
	}
	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}
	public String getFcontent() {
		return fcontent;
	}
	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public Timestamp getFdate() {
		return fdate;
	}
	public void setFdate(Timestamp fdate) {
		this.fdate = fdate;
	}
	
	
	
}
