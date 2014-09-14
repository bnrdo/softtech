package com.softtech.web.controller;

import static com.softtech.web.util.Quickies.isNull;
import static com.softtech.web.util.Quickies.list;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.web.form.Registration;
import com.softtech.web.validation.Step1Validation;
import com.softtech.web.validation.Step2Validation;
import com.softtech.web.validation.Step3Validation;

@Controller
@SessionAttributes({"registrationForm"})
public class RegistrationController {
	
	private static final String FORM_REGISTRATION = "registrationForm";
	
	@RequestMapping(value = "/show-step-1", method = RequestMethod.GET)
	protected String showStep1(ModelMap model){
		
		
		if(isNull(model.get(FORM_REGISTRATION))){
			model.addAttribute(FORM_REGISTRATION, new Registration());
		}
		
		populateCategoryAndTech(model);
		
		return "register-step-1";
	}
	
	@RequestMapping(value = "/register-step-1", method = RequestMethod.POST)
	protected String processStep1(@Validated(Step1Validation.class) @ModelAttribute(FORM_REGISTRATION) Registration registrationForm,
									BindingResult binding, ModelMap model){
		
		if(binding.hasErrors()){
			populateCategoryAndTech(model);
			
			return "register-step-1";
		}
		
		return "redirect:/show-step-2";
		
	}
	
	@RequestMapping(value = "/show-step-2", method = RequestMethod.GET)
	protected String showStep2(){
		return "register-step-2";
	}
	
	@RequestMapping(value = "/register-step-2", method = RequestMethod.POST)
	protected String processStep2(@Validated(Step2Validation.class) @ModelAttribute(FORM_REGISTRATION) Registration registrationForm){
		MultipartFile file = registrationForm.getResume();
		
		if (!file.isEmpty()) {
			try{
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "softtech-uploaded-resumes");
                
                if (!dir.exists()){
                    dir.mkdirs();
                }
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
		
		return "redirect:/show-step-3";
	}
	
	@RequestMapping(value = "/show-step-3", method = RequestMethod.GET)
	protected String showStep3(){
		return "register-step-3";
	}
	
	@RequestMapping(value = "/register-step-3", method = RequestMethod.POST)
	protected String processStep3(@Validated(Step3Validation.class) @ModelAttribute(FORM_REGISTRATION) Registration registrationForm,
									BindingResult binding){
		
		/*PasswordMatch pmValidator = new PasswordMatch();
		pmValidator.validate(registrationForm, binding); */
		
		if(binding.hasErrors()){
			return "register-step-3";
		}
		
		return "redirect:/show-step-1";
	}
	
	private void populateCategoryAndTech(ModelMap model){
		model.addAttribute("categoryList", list("Select Category", "Category 1", "Category 2", "Category 3", "Category 4"));
		model.addAttribute("technologyList", list("Select Technology", "Technology 2", "Technology 3", "Technology 4"));
	}
}
