package edu.auburn.domain;

public class ExamVideo {
//	fid integer auto_increment primary key,
//	name varchar(100),
//	path varchar(1000),
//	ftype varchar(10),
//	fdesc varchar(2000),
//	eid integer
	private int vid;
	private String name;
	private String path;
	private String type;
	private String desc;
	private int eid;
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	@Override
	public String toString() {
		return "ExamVideo [vid=" + vid + ", name=" + name + ", path=" + path + ", type=" + type + ", desc=" + desc
				+ ", eid=" + eid + "]";
	}
	
}
