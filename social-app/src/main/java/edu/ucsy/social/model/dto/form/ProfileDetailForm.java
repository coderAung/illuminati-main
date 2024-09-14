package edu.ucsy.social.model.dto.form;

import java.io.Serializable;
import java.time.LocalDate;

import edu.ucsy.social.model.entity.UserDetail.Gender;
import edu.ucsy.social.model.entity.UserDetail.Occupation;
import edu.ucsy.social.model.entity.UserDetail.Relationship;

public class ProfileDetailForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private String name;
	private String phoneNumber;
	private String address;
	private LocalDate birthDate;
	private String bio;
	private Gender gender;
	private Relationship relationship;
	private Occupation occupation;

	public ProfileDetailForm(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

}
