package per.wxl.myBlog.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Login {

  private User user;
  private String loginIp;
  private Date loginTime;


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
  public Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
  }

}
