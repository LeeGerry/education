package edu.auburn.domain;

public class WordVideo {
	private int vid;
	private int wid;
	private int eid;
	private String name;
	private String path;
	private String desc;
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "WordVideo [vid=" + vid + ", wid=" + wid + ", eid=" + eid + ", name=" + name + ", path=" + path
				+ ", desc=" + desc + "]";
	}
	
}
