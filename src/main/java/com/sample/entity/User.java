package com.sample.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries(value = {
    @NamedQuery(name = User.FETCH_USER_PROJECTS,
        query = "" + " select t from User as t"
            + " left join fetch t.projects p where :userId = t.userId" + " order by p.projectId"),
    @NamedQuery(name = User.FETCH_USERS_PROJECTS_BY_TYPE,
        query = "" + " select t from User as t"
            + " left join fetch t.projects p where :type = t.type" + " order by p.projectId"),
    @NamedQuery(name = User.FETCH_USERS_TYPE,
        query = "" + " select t from User as t where :type = t.type")})
public class User implements Serializable {

  public static final String FETCH_USER_PROJECTS = "fetchUserProjects";
  public static final String FETCH_USERS_PROJECTS_BY_TYPE = "fetchUsersProjectsByType";
  public static final String FETCH_USERS_TYPE = "fetchUsersByType";
  public static final String INSERT_USER = "insertUser";

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "userId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;

  @Column(name = "name")
  private String name = "";

  @Column(name = "type")
  private String type = "";

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private List<Project> projects = new ArrayList<Project>();

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
