package com.softtech.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping({"/index", "/"})
	public String setupForm(Map<String, Object> map){
		return "index";
	}
}
