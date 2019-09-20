package per.wxl.myBlog.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Discuss {

  private Integer discussId;
  private String discussBody;
  private Date discussTime;
  private User user;
  private List<Reply> replies;


  public Integer getDiscussId() {
    return discussId;
  }

  public void setDiscussId(Integer discussId) {
    this.discussId = discussId;
  }

  public String getDiscussBody() {
    return discussBody;
  }

  public void setDiscussBody(String discussBody) {
    this.discussBody = discussBody;
  }

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
  public Date getDiscussTime() {
    return discussTime;
  }

  public void setDiscussTime(Date discussTime) {
    this.discussTime = discussTime;
  }


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Reply> getReplies() {
    return replies;
  }

  public void setReplies(List<Reply> replies) {
    this.replies = replies;
  }
}
