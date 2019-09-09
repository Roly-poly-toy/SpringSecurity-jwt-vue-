package per.wxl.myBlog.model;


import java.util.Date;

public class Reply {

  private Integer replyId;
  private String replyBody;
  private Date replyTime;
  private User user;
  private Reply reply;


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
