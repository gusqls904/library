package com.library.wol.model;

public class LoanDto {

	private int no;
	private String User_id;
	private String book_id;
	private String book_name;
	private String loan_date;
	private String term;
	private String return_date;
	private int arrears;
	private int state;
	private String datediff;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUser_id() {
		return User_id;
	}
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getLoan_date() {
		return loan_date;
	}
	public void setLoan_date(String loan_date) {
		this.loan_date = loan_date;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	public int getArrears() {
		return arrears;
	}
	public void setArrears(int arrears) {
		this.arrears = arrears;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getDatediff() {
		return datediff;
	}

	public void setDatediff(String datediff) {
		this.datediff = datediff;
	}



}
