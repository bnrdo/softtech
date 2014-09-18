package com.softtech.web.controller;

import static com.softtech.web.util.Qu.list;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softtech.web.form.SearchResume;
import com.softtech.web.model.TableData;
import com.softtech.web.util.Qu;

@Controller
@RequestMapping("/recruiter")
public class SearchResumeController {
	
	
	@RequestMapping(value = "/show-search-resume", method = RequestMethod.GET)
	protected String showSearchResume(ModelMap model){
		
		model.addAttribute("categoryList", list("Select Category", "Category 1", "Category 2", "Category 3", "Category 4"));
		model.addAttribute("searchForm", new SearchResume());
		
		return "recruiter/searchResume";
	}
	
	@RequestMapping(value = "/process-search-resume", method = RequestMethod.POST)
	protected String processSearchResume(@ModelAttribute("searchForm") SearchResume searchForm, ModelMap model){
		
		model.addAttribute("url", 
				"/recruiter/search-resume-ajax?title=" 
				+ searchForm.getTitle() 
				+ "&category=" 
				+ searchForm.getCategory());
		
		
		
		return "recruiter/searchResumeResults";
	}
	
	@ResponseBody
	@RequestMapping(value = "/search-resume-ajax", method = RequestMethod.GET)
	protected TableData<DummyResume> searchResumeAjax(){
		return new TableData<DummyResume>(createDummyResumeList());
	}
	
	private static List<DummyResume> createDummyResumeList(){
		return Qu.list(new DummyResume("556671", "Bernardo", "Santos", "96359814"),
						new DummyResume("123332", "Joseph", "Bagnes", "9642221"),
						new DummyResume("000911", "Charles", "Vicencio", "96722214"),
						new DummyResume("200921", "Robert", "Erasquin", "9885522"),
						new DummyResume("222711", "Jacqui Lou", "Lunod", "9650009"),
						new DummyResume("200093", "Chris", "Fermin", "09221111"));
	}
}


class DummyResume{
	
	private String accountId;
	private String firstName;
	private String lastName;
	private String contact;
	
	public DummyResume(String accountId, String firstName, String lastName, String contact){
		this.accountId = accountId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}