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
  private Blog blog;
  private Integer discussReplyCount;

  public Integer getDiscussReplyCount() {
    return discussReplyCount;
  }

  public void setDiscussReplyCount(Integer discussReplyCount) {
    this.discussReplyCount = discussReplyCount;
  }

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

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

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
