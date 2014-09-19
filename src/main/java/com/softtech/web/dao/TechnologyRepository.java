package com.softtech.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.web.model.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Integer>{

}
