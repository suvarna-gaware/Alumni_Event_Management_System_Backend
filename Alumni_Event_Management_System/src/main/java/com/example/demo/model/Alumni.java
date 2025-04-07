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

}
