package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Alumni {
	private int Alumniid;
	private int Did;
	private String name;
	private String email;
	private String contact;
	private String address;
	private String gender;
	private int year;
	@Override
	public String toString() {
		return "Alumni [Alumniid=" + Alumniid + ", Did=" + Did + ", name=" + name + ", email=" + email + ", contact="
				+ contact + ", address=" + address + ", gender=" + gender + ", year=" + year + "]";
		
	}
	public int getAlumniid() {
		return Alumniid;
	}
	public void setAlumniid(int alumniid) {
		Alumniid = alumniid;
	}
	public int getDid() {
		return Did;
	}
	public void setDid(int did) {
		Did = did;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
