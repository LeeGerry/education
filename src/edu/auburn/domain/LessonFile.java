package edu.auburn.domain;

public class LessonFile {
	private int fid;
	private String name;
	private String path;
	private String ftype;
	private String fdesc;
	private int lid;

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
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

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	@Override
	public String toString() {
		return "LessonFile [fid=" + fid + ", name=" + name + ", path=" + path + ", ftype=" + ftype + ", fdesc=" + fdesc
				+ ", lid=" + lid + "]";
	}

}
