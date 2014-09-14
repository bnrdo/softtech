package com.softtech.web.validation;

import javax.validation.ConstraintValidatorContext;

import com.softtech.web.annotation.PasswordMatch;

public class PasswordMatchValidator extends FieldMatchValidator{

	@Override
	public void fieldsDontMatchAction(Object value,
			ConstraintValidatorContext context) {
		//since this is a class level constraint, do this to show error message in the 
		//chosen form property (addPropertyNode)
		//it will be added to global errors if this is not done
		context.disableDefaultConstraintViolation();
		context
			.buildConstraintViolationWithTemplate("Passwords do not match")
			.addPropertyNode(RETYPE_FIELD)
			.addConstraintViolation();
		context
			.buildConstraintViolationWithTemplate("")
			.addPropertyNode(PASSWORD_FIELD)
			.addConstraintViolation();
	}
}
