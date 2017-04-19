package com.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.entity.User;
import com.sample.repository.UserDao;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/api")
public class RestResource {

  @Autowired
  private UserDao userDao;

  @GET
  @Path("get-user")
  public String getUser() {
    return userDao.getByUserId(Integer.valueOf(1)).getName();
  }

  @GET
  @Path("create-user")
  public String createUser() {
    User user = new User();
    user.setName("totta8");
    user.setUserId(Integer.valueOf(8));
    user.setType("x");
    userDao.createUser(user);
    return "";
  }

}
