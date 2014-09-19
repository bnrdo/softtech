package com.softtech.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softtech.web.form.SearchResume;
import com.softtech.web.model.JobSeekerProfile;
import com.softtech.web.model.TableData;
import com.softtech.web.service.JobSeekerProfileService;

@Controller
@RequestMapping("/recruiter")
public class SearchResumeController {
	
	@Inject
	private JobSeekerProfileService jobSeekerServ;
	
	@RequestMapping(value = "/show-all-resume", method = RequestMethod.GET)
	protected String showAllResume(@ModelAttribute("searchForm") SearchResume searchForm, ModelMap model){
		
		return "recruiter/searchResumeResults";
	}
	
	@ResponseBody
	@RequestMapping(value = "/search-resume-ajax", method = RequestMethod.GET)
	protected TableData<JobSeekerProfile> searchResumeAjax(){
		return new TableData<JobSeekerProfile>(jobSeekerServ.findAll());
	}
}