package com.spring.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	@Column(name = "is_per_or_cur")
	private String isPermanentOrCurrent;
	
	@Column(name = "address_street",length = 500)
    private String addressStreet;

    @Column(name = "address_line1",length = 500)
    private String addressLine1;

    @Column(name = "address_line2",length = 500)
    private String addressLine2;

    @Column(name = "address_city",length = 500)
    private String addressCity;

    @Column(name = "address_state",length = 500)
    private String addressState;

    @Column(name = "address_pincode")
    private String addressPincode;

    
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Address(Long addressId, String isPermanentOrCurrent, String addressStreet, String addressLine1,
			String addressLine2, String addressCity, String addressState, String addressPincode) {
		super();
		this.addressId = addressId;
		this.isPermanentOrCurrent = isPermanentOrCurrent;
		this.addressStreet = addressStreet;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressPincode = addressPincode;
	}


	public Address(String isPermanentOrCurrent, String addressStreet, String addressLine1, String addressLine2,
			String addressCity, String addressState, String addressPincode) {
		super();
		this.isPermanentOrCurrent = isPermanentOrCurrent;
		this.addressStreet = addressStreet;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressPincode = addressPincode;
	}


	public Long getAddressId() {
		return addressId;
	}


	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}


	public String getIsPermanentOrCurrent() {
		return isPermanentOrCurrent;
	}


	public void setIsPermanentOrCurrent(String isPermanentOrCurrent) {
		this.isPermanentOrCurrent = isPermanentOrCurrent;
	}


	public String getAddressStreet() {
		return addressStreet;
	}


	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getAddressCity() {
		return addressCity;
	}


	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}


	public String getAddressState() {
		return addressState;
	}


	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}


	public String getAddressPincode() {
		return addressPincode;
	}


	public void setAddressPincode(String addressPincode) {
		this.addressPincode = addressPincode;
	}


	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", isPermanentOrCurrent=" + isPermanentOrCurrent + ", addressStreet="
				+ addressStreet + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressCity="
				+ addressCity + ", addressState=" + addressState + ", addressPincode=" + addressPincode + "]";
	}

	
    
}
