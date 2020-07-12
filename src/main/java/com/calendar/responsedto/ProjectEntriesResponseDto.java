package com.calendar.responsedto;

public class ProjectEntriesResponseDto {
	
	private int entryId;
	
	private String title;
	
	private boolean isClosed;
	
	private Integer sortNumber;
	
	public ProjectEntriesResponseDto(int entryId, String title, boolean isClosed, Integer sortNumber) {
		this.entryId = entryId;
		this.title = title;
		this.isClosed = isClosed;
		this.sortNumber = sortNumber;
	}
	
	

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
}
