package com.softtech.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;









import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softtech.web.annotation.PasswordMatch;
import com.softtech.web.form.Registration;
import com.softtech.web.service.UserAccountService;

public abstract class FieldMatchValidator implements ConstraintValidator<PasswordMatch, Object>{
	
	protected String PASSWORD_FIELD;
	protected String RETYPE_FIELD;
	
	@Override
	public void initialize(PasswordMatch constraintAnnotation) {
		PASSWORD_FIELD 	= "password";
		RETYPE_FIELD 	= "retypePassword";
	}
	
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context){
        try{
            final Object password = BeanUtils.getProperty(value, PASSWORD_FIELD);
            final Object retypePassword = BeanUtils.getProperty(value, RETYPE_FIELD);
			
			boolean fieldsMatch = true;
			
			fieldsMatch = password == null && retypePassword == null || password != null && password.equals(retypePassword);
			
			if(false == fieldsMatch) {
				
				fieldsDontMatchAction(value, context);
				
				return false;
			}
			
			return true;
        }
        catch (final Exception ignore){
            // ignore
        }
        
        return true;
    }

	public abstract void fieldsDontMatchAction(final Object value, final ConstraintValidatorContext context);
}