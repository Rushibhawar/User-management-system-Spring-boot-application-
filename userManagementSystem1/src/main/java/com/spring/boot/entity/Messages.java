package com.spring.boot.entity;

public class Messages {

	private String message;
	private String cssBootStrapClass;
	private String cssErroOrSuccess;
	public Messages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Messages(String message, String cssBootStrapClass, String cssErroOrSuccess) {
		super();
		this.message = message;
		this.cssBootStrapClass = cssBootStrapClass;
		this.cssErroOrSuccess = cssErroOrSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCssBootStrapClass() {
		return cssBootStrapClass;
	}
	public void setCssBootStrapClass(String cssBootStrapClass) {
		this.cssBootStrapClass = cssBootStrapClass;
	}
	public String getCssErroOrSuccess() {
		return cssErroOrSuccess;
	}
	public void setCssErroOrSuccess(String cssErroOrSuccess) {
		this.cssErroOrSuccess = cssErroOrSuccess;
	}
	@Override
	public String toString() {
		return "Messages [message=" + message + ", cssBootStrapClass=" + cssBootStrapClass + ", cssErroOrSuccess="
				+ cssErroOrSuccess + "]";
	}
	
	
	
}
