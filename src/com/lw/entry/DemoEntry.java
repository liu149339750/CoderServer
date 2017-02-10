package com.lw.entry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class DemoEntry {

	private String title;
	private String apk;
	private String icon;
	private String detail;
	private String minversion;
	private String openlink;
	private long size;
	
	private int id;

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getApk() {
		return apk;
	}

	public void setApk(String apk) {
		this.apk = apk;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getMinversion() {
		return minversion;
	}

	public void setMinversion(String minversion) {
		this.minversion = minversion;
	}

	public String getOpenlink() {
		return openlink;
	}

	public void setOpenlink(String openlink) {
		this.openlink = openlink;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "ttitle:"+title + ",icon:"+icon;
	}
}
