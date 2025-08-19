package com.gabriel.librms.model;
import lombok.Data;
import java.util.Date;

@Data
public class LibraryTracker{
	private int id;
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
}
