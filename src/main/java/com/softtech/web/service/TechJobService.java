package com.softtech.web.service;

import java.util.List;

import com.softtech.web.model.TechJob;

public interface TechJobService {
	void addTechJob(TechJob techJob);
	void updateTechJob(TechJob techJob);
	void deleteTechJob(TechJob techJob);
	
	List<TechJob> findAll();
}
