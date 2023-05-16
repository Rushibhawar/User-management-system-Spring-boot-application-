package com.spring.boot.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_first_name", length = 50)
	private String userFirstName;
	
	@Column(name = "user_last_name", length = 50)
	private String userLastName;
	
	@Column(name = "user_date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date userDateOfBirth;
	
	@Column(name = "user_email",unique = true)
	private String userEmail;
	
	@Column(name = "user_password", length = 512)
	private String userPassword;
	
	@Column(name = "user_mobile_number", length = 10)
	private String userMobileNumber;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "user_created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userCreatedDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private List<Address> address = new ArrayList<>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String userFirstName, String userLastName, Date userDateOfBirth, String userEmail,
			String userPassword, String userMobileNumber, boolean isActive, Date userCreatedDate,
			List<Address> address) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userDateOfBirth = userDateOfBirth;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userMobileNumber = userMobileNumber;
		this.isActive = isActive;
		this.userCreatedDate = userCreatedDate;
		this.address = address;
	}

	public User(String userFirstName, String userLastName, Date userDateOfBirth, String userEmail, String userPassword,
			String userMobileNumber, boolean isActive, Date userCreatedDate, List<Address> address) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userDateOfBirth = userDateOfBirth;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userMobileNumber = userMobileNumber;
		this.isActive = isActive;
		this.userCreatedDate = userCreatedDate;
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public Date getUserDateOfBirth() {
		return userDateOfBirth;
	}

	public void setUserDateOfBirth(Date userDateOfBirth) {
		this.userDateOfBirth = userDateOfBirth;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.userPassword = encoder.encode(userPassword);
	}

	public String getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getUserCreatedDate() {
		return userCreatedDate;
	}

	public void setUserCreatedDate(Date userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userDateOfBirth=" + userDateOfBirth + ", userEmail=" + userEmail + ", userPassword=" + userPassword
				+ ", userMobileNumber=" + userMobileNumber + ", isActive=" + isActive + ", userCreatedDate="
				+ userCreatedDate + ", address=" + address + "]";
	}

	
	
}
