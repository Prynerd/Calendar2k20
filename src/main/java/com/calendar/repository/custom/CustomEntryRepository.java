package com.calendar.repository.custom;

import java.util.List;

import com.calendar.domain.Entry;

public interface CustomEntryRepository {

	List<Entry> getEntriesByUserId(int userId);
	
	List<Entry> getProjectsByUserIdAndStatus(int userId, boolean isFinished);
	
	List<Entry> getOrderedEntriesByUserId(int userId);
	
	void removeEntry(Entry entry);
	
}
