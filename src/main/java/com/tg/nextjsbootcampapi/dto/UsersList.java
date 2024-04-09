package com.tg.nextjsbootcampapi.dto;

import com.tg.nextjsbootcampapi.model.User;

import java.util.List;

public class UsersList {


  private List<User> userList;

  public List<User> getUserList() {
    return userList;
  }
  public void setUserList(List<User> userList) {
    this.userList = userList;
  }


}
