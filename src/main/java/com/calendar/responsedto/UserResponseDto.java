package com.calendar.responsedto;

public class UserResponseDto {
	
	private int id;
    
    private String email;
    
    private boolean isValidated;

    private boolean onlyActiveProjects;
    
    public UserResponseDto() {
    	
    }

    public UserResponseDto(int id, String email, boolean isValidated, boolean onlyActiveProjects) {
        this.id = id;
        this.email = email;
        this.isValidated = isValidated;
        this.onlyActiveProjects = onlyActiveProjects;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsValidated() {
        return isValidated;
    }

    public void setIsValidated(boolean isValidated) {
        this.isValidated = isValidated;
    }

    public boolean isOnlyActiveProjects() {
        return onlyActiveProjects;
    }

    public void setOnlyActiveProjects(boolean onlyActiveProjects) {
        this.onlyActiveProjects = onlyActiveProjects;
    }
}
