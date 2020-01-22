package com.calendar.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.calendar.domain.Entry;
import com.calendar.repository.custom.CustomEntryRepository;

@Repository
public class CustomEntryRepositoryImpl implements CustomEntryRepository{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Entry> getEntitiesByUserId(int userId) {
		return em
        	.createQuery("SELECT e FROM Entry e WHERE e.userId = :id", Entry.class)
        	.setParameter("id", userId)
        	.getResultList();
	}

}
