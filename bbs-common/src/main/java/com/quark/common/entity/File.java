package com.quark.common.entity;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "upload_file")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class File {
	@Id
	private String fileName;
	@Column
	private String md5;
	@Column
	private Date uploadDate;

	@Override
	public String toString() {
		return "File{" +
				"fileName='" + fileName + '\'' +
				", MD5='" + md5 + '\'' +
				", uploadDate=" + uploadDate +
				'}';
	}

	public File(String fileName, String MD5, Date uploadDate) {
		this.fileName = fileName;
		this.md5 = MD5;
		this.uploadDate = uploadDate;
	}

	public File() {
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
}
