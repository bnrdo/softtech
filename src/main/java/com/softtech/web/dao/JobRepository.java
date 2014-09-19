package com.softtech.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.web.model.Job;

public interface JobRepository extends JpaRepository<Job, Integer>{

}
