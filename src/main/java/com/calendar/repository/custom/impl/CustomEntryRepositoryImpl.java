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
	public List<Entry> getEntriesByUserId(int userId) {
		return em
        	.createQuery("SELECT e FROM Entry e WHERE e.userId = :id AND e.isChild = :isC", Entry.class)
        	.setParameter("id", userId)
        	.setParameter("isC", false)
        	.getResultList();
	}
	
	@Override
	public List<Entry> getEntriesByUserIdAndStatus(int userId, boolean isFinished) {
		return em
				.createQuery("SELECT e FROM Entry e WHERE e.userId = :id AND e.isChild = :isC AND e.isFinished = :isF", Entry.class)
				.setParameter("id", userId)
				.setParameter("isC", false)
				.setParameter("isF", isFinished)
				.getResultList();
	}

}
