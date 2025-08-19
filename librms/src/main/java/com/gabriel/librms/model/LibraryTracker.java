package com.gabriel.librms.model;
import lombok.Data;
import java.util.Date;

@Data
public class LibraryTracker{
	private String id;
	private String title;
	private String author;
	private String ISBN;
	private String borrowerName;
	private String borrowerEmail;
	private String borrowerPhoneNumber;
	private Date borrowDate;
	private Date returnDate;
	private Date lastUpdated;
	private Date created;
	@Override
	public String toString(){
		return title;
	}

	public String getValue() {
        return id;
    }

	public void setId(String s) {
		this.id = id;
	}
}
