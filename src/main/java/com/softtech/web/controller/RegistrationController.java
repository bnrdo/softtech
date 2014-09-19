package com.softtech.web.controller;

import static com.softtech.web.util.Qu.isNull;
import static com.softtech.web.util.Qu.list;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.web.form.Registration;
import com.softtech.web.service.UserAccountService;
import com.softtech.web.validation.Step1Validation;
import com.softtech.web.validation.Step2Validation;
import com.softtech.web.validation.Step3Validation;

@Controller
@SessionAttributes({"registrationForm", "mode"}) //mode is for step 3
public class RegistrationController {
	
	private enum RegistrationStep{ Step1, Step2, Step3};
	
	private static final String MODE_REG_USE_EXISTING = "UseExisting";
	private static final String MODE_REG_CREATE_NEW = "CreateNew";
	
	@Inject
	private UserAccountService accountServ;
	
	private static final String FORM_REGISTRATION = "registrationForm";
	
	@RequestMapping(value = "/show-step-1", method = RequestMethod.GET)
	protected String showStep1(ModelMap model){
		
		
		
		if(isNull(model.get(FORM_REGISTRATION))){
			model.addAttribute(FORM_REGISTRATION, new Registration());
		}
		
		model.addAttribute("step", RegistrationStep.Step1);
		
		populateCategoryAndTech(model);
		
		return "registration";
	}
	
	@RequestMapping(value = "/register-step-1", method = RequestMethod.POST)
	protected String processStep1(@Validated(Step1Validation.class) 
									@ModelAttribute(FORM_REGISTRATION) 
									Registration registrationForm,
									BindingResult binding, ModelMap model){
		
		if(binding.hasErrors()){
			
			model.addAttribute("step", RegistrationStep.Step1);
			
			return "registration";
		}
		
		return "redirect:/show-step-2";
		
	}
	
	@RequestMapping(value = "/show-step-2", method = RequestMethod.GET)
	protected String showStep2(ModelMap model){
		
		model.addAttribute("step", RegistrationStep.Step2);
		
		return "registration";
	}
	
	@RequestMapping(value = "/register-step-2", method = RequestMethod.POST)
	protected String processStep2(@Validated(Step2Validation.class) 
									@ModelAttribute(FORM_REGISTRATION) 
									Registration registrationForm){
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
	protected String showStep3(ModelMap model){
		
		model.addAttribute("step", RegistrationStep.Step3);
		
		return "registration";
	}
	
	@RequestMapping(value = "/register-step-3", method = RequestMethod.POST)
	protected String processStep3(@Validated(Step3Validation.class) 
									@ModelAttribute(FORM_REGISTRATION) 
									Registration registrationForm,
									BindingResult binding,
									HttpSession session,
									ModelMap model){
		
		if(binding.hasErrors()){
			
			model.addAttribute("step", RegistrationStep.Step3);
			
			return "registration";
		}
		
		/*accountServ.addUserAccount(
				accountServ.toUserAccount(registrationForm));*/
		
		if(MODE_REG_CREATE_NEW.equals(model.get("mode"))){
			//authenticate if logged in or not?
		}else{
			//send email verification
		}
		
		cleanUp(session, model);
		
		return "redirect:/show-register-success";
	}
	
	@RequestMapping(value = "/show-register-success", method = RequestMethod.GET)
	protected String showRegisterSuccess(ModelMap model){
		
		model.addAttribute("successMessage", "Registration Success!<br/>Account verification link was sent to your email ");
		
		return "success";
	}
	
	@RequestMapping(value = "/regist-switch-mode", method = RequestMethod.GET)
	@ResponseBody
	protected void changeMode(@RequestParam String mode, ModelMap model){
		model.addAttribute("mode", mode);
	}
	
	@RequestMapping(value = "/regist-preserve-form-then-show-step-2", method = RequestMethod.POST)
	@ResponseBody
	protected void preserveFormThenShowStep2( @RequestBody Registration preserveForm, ModelMap model){
		Registration form = (Registration) model.get(FORM_REGISTRATION);
		
		if(MODE_REG_USE_EXISTING.equals(model.get("mode"))){
			preserveForm = null;
		}
		
		Registration.copyCompleteRegData(preserveForm, form);
	}
	
	
	
	private void cleanUp(HttpSession session, ModelMap model){
		session.removeAttribute(FORM_REGISTRATION);
		model.remove(FORM_REGISTRATION);
	}
	
	private void populateCategoryAndTech(ModelMap model){
		model.addAttribute("categoryList", list("Select Category", "Category 1", "Category 2", "Category 3", "Category 4"));
		model.addAttribute("technologyList", list("Select Technology", "Technology 2", "Technology 3", "Technology 4"));
	}
}
