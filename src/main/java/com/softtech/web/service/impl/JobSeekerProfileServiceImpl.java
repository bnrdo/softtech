package com.softtech.web.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.softtech.web.dao.JobSeekerProfileRepository;
import com.softtech.web.model.JobSeekerProfile;
import com.softtech.web.service.JobSeekerProfileService;
@Service
public class JobSeekerProfileServiceImpl implements JobSeekerProfileService {
	
	@Inject
	private JobSeekerProfileRepository repo;

	@Override
	public void addJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<JobSeekerProfile> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
