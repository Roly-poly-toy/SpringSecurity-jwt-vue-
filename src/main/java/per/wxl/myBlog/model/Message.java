package per.wxl.myBlog.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Message {

  private Integer messageId;
  private User user;
  private String messageBody;
  private Date messageTime;


  public Integer getMessageId() {
    return messageId;
  }

  public void setMessageId(Integer messageId) {
    this.messageId = messageId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getMessageBody() {
    return messageBody;
  }

  public void setMessageBody(String messageBody) {
    this.messageBody = messageBody;
  }

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
  public Date getMessageTime() {
    return messageTime;
  }

  public void setMessageTime(Date messageTime) {
    this.messageTime = messageTime;
  }

}
