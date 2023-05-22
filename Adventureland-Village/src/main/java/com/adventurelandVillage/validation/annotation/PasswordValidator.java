package com.adventurelandVillage.validation.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

	@Override
	public boolean isValid(String passowrd, ConstraintValidatorContext context) {
		
		Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
		Matcher matcher = pattern.matcher(passowrd);
		return matcher.matches();
	}

}
