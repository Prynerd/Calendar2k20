package com.calendar.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.calendar.repository.custom.CustomUserRepository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void setOnlyActiveProjectById(int id, boolean status) {
		
	Query q = em.createQuery("UPDATE User SET onlyActiveProjects = :status WHERE id = :id");
	q.setParameter("id", id);
	q.setParameter("status", status);
	q.executeUpdate();
	
	}

	
	
}
