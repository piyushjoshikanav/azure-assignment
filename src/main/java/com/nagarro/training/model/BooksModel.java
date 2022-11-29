package com.nagarro.training.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Data
@Entity
public class BooksModel {
	@Id
	private int bookCode;
	private String bName;
	private String Author;
	@Temporal(TemporalType.DATE)
	private Date date;

	public String printDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		String currDate = dateFormat.format(date);
		return currDate;
	}

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public String getbName() {
		return bName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}
//public String get	Date() {
//	return date;
//}
//public void setDate(String date) {
//	this.date = date;
//}

}
