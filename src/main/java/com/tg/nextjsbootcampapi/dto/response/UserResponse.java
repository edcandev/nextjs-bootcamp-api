package com.tg.nextjsbootcampapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tg.nextjsbootcampapi.model.JobTitle;
import jakarta.persistence.Column;

public class UserResponse {

  private Integer id;
  private String email;
  private Boolean active;

  @JsonProperty("first_name")
  private String firstName;
  @JsonProperty("last_name")
  private String lastName;
  @JsonProperty("job_title")
  private JobTitle jobTitle;
  @JsonProperty("has_project")
  private Boolean hasProject;

  public UserResponse(Integer id, String email, Boolean active, String firstName, String lastName, JobTitle jobTitle, Boolean hasProject) {
    this.id = id;
    this.email = email;
    this.active = active;
    this.firstName = firstName;
    this.lastName = lastName;
    this.jobTitle = jobTitle;
    this.hasProject = hasProject;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public JobTitle getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(JobTitle jobTitle) {
    this.jobTitle = jobTitle;
  }

  public Boolean getHasProject() {
    return hasProject;
  }

  public void setHasProject(Boolean hasProject) {
    this.hasProject = hasProject;
  }
}
