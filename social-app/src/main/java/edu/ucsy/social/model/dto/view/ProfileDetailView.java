package edu.ucsy.social.model.dto.view;

import java.time.LocalDate;

import edu.ucsy.social.model.entity.UserDetail.Gender;
import edu.ucsy.social.model.entity.UserDetail.Occupation;
import edu.ucsy.social.model.entity.UserDetail.Relationship;

public class ProfileDetailView {

	private int id;
	private String email;
	private String name;
	private LocalDate birthDate;
	private String address;
	private String bio;
	private String phoneNumber;
	private Gender gender;
	private Relationship relationship;
	private Occupation occupation;

	public ProfileDetailView() {
	}

	public ProfileDetailView(int id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
