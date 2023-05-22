package com.adventurelandVillage.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValidation {
	
	public String message() default "Password must be of at least 8 characters, contains at least one uppercase, one lowercase,a special character and a number in it";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
