package com.softtech.web.service;

import java.util.List;

import com.softtech.web.model.Job;

public interface JobService {
	void addJob(Job job);
	void updateJob(Job job);
	void deleteJob(Job job);
	
	List<Job> findAll();	
}
