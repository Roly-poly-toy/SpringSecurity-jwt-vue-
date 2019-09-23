package per.wxl.myBlog.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Reply {

  private Integer replyId;
  private String replyBody;
  private Date replyTime;
  private User user;
  private Reply reply;
  private Discuss discuss;

  public Discuss getDiscuss() {
    return discuss;
  }

  public void setDiscuss(Discuss discuss) {
    this.discuss = discuss;
  }

  public Integer getReplyId() {
    return replyId;
  }

  public void setReplyId(Integer replyId) {
    this.replyId = replyId;
  }

  public String getReplyBody() {
    return replyBody;
  }

  public void setReplyBody(String replyBody) {
    this.replyBody = replyBody;
  }

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
  public Date getReplyTime() {
    return replyTime;
  }

  public void setReplyTime(Date replyTime) {
    this.replyTime = replyTime;
  }


  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Reply getReply() {
    return reply;
  }

  public void setReply(Reply reply) {
    this.reply = reply;
  }
}
