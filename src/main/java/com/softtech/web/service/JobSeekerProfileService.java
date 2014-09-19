package com.softtech.web.service;

import java.util.List;

import com.softtech.web.model.JobSeekerProfile;

public interface JobSeekerProfileService {
	void addJobSeekerProfile(JobSeekerProfile jobSeekerProfile);
	void updateJobSeekerProfile(JobSeekerProfile jobSeekerProfile);
	void deleteJobSeekerProfile(JobSeekerProfile jobSeekerProfile);
	
	List<JobSeekerProfile> findAll();	
}
