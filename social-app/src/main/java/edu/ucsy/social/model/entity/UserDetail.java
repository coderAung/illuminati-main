package edu.ucsy.social.model.entity;

import java.time.LocalDate;

import edu.ucsy.social.data.annotation.Entity;

@Entity
public record UserDetail(
		long userId,
		LocalDate birthDate,
		String address,
		String bio,
		String phoneNumber,
		Gender gender,
		Relationship relationship,
		Occupation occupation) {

	public enum Relationship {
		Single("Single"), 
		Married("Married"), 
		Divorced("Divorced"), 
		InARelationship("In A Relationship"),
		IsComplicated("Is Complicated");
		
		private String message;
		
		private Relationship(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return this.message;
		}
	}
	
	public enum Occupation {
		Student, 
		Actor,
		Singer, 
		Dancer,
		Influencer,
		DigitalCreator
	}
	
	public enum Gender {
		Male, Female, Others
	}
}
