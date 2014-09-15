package com.softtech.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;









import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softtech.web.annotation.PasswordMatch;
import com.softtech.web.annotation.UserExist;
import com.softtech.web.form.Registration;
import com.softtech.web.service.UserAccountService;

import static com.softtech.web.util.Qu.*;

public class UserExistValidator implements ConstraintValidator<UserExist, Object>{
	
	@Autowired
	private UserAccountService accountService;

    @Override
    public void initialize(UserExist constraintAnnotation){
    	
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context){
    	
    	String username = (String) value;
    	
    	//@NotEmpty will handle it
    	if("".equals(username.trim())){
    		return true;
    	}
    	
    	if(notNull(accountService.findUserAccountByName(username))){
    		return false;
    	}
    	
       return true;
    }
}