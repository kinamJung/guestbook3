package com.hanains.guestbook.vo;

public class GuestBookVo {

	private Long no;
	private String name;
	private String password;
	private String message;
	private String date;
	
	
	
	public GuestBookVo() {
		
	}
	public GuestBookVo(Long no, String name, String password, String message,
			String date) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.message = message;
		this.date = date;
	}
	public GuestBookVo(String name, String password, String message) {
		super();
		this.name = name;
		this.password = password;
		this.message = message;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", password="
				+ password + ", message=" + message + ", date=" + date + "]";
	}
	
	
	
}
