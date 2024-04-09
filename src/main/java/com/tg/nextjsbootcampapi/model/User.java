package com.tg.nextjsbootcampapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


//@Entity
//@Table(name = "users")
public class User implements UserDetails {


  //@Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String email;

    //@Column(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;


  //@Column(name = "last_name")
  @JsonProperty("last_name")
  private String lastName;


  //@Enumerated(EnumType.STRING)
  //@Column(name = "job_title")
  @JsonProperty("job_title")
  private JobTitle jobTitle;

  private String password;

  public void setPassword(String password) {
    this.password = password;
  }

  //@Column(name = "has_project")
  @JsonProperty("has_project")
  //@Convert( converter = NumericBooleanConverter.class)
  private Boolean hasProject = false;

  //@Column(name = "active")
  //@Convert( converter = NumericBooleanConverter.class)
  private Boolean active = true;

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }



  public User() {}

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

  public Boolean getHasProject() {
    return hasProject;
  }

  public void setHasProject(Boolean hasProject) {
    this.hasProject = hasProject;
  }

  public JobTitle getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(JobTitle jobTitle) {
    this.jobTitle = jobTitle;
  }

  public User(Boolean hasProject) {
    this.hasProject = hasProject;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", jobTitle=" + jobTitle +
        ", hasProject=" + hasProject +
        '}';
  }


  public User(Integer id, String email, String firstName, String lastName, JobTitle jobTitle, String password, Boolean hasProject, Boolean active) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.jobTitle = jobTitle;
    this.password = password;
    this.hasProject = hasProject;
    this.active = active;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(jobTitle.name()));
  }


  public String getPassword() {
    return this.password;
  }

  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.getActive();
  }
}
