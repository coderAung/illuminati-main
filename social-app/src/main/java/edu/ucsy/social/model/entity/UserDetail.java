package edu.ucsy.social.model.entity;

import java.time.LocalDate;

public record UserDetail(
		long userid,
		LocalDate birthDate,
		String address) {

}
