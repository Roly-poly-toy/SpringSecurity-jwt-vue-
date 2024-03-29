package per.wxl.myBlog.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Announce {

  private Integer announceId;
  private String announceTitile;
  private String announceBody;
  private Date announceTime;


  public Integer getAnnounceId() {
    return announceId;
  }

  public void setAnnounceId(Integer announceId) {
    this.announceId = announceId;
  }

  public String getAnnounceTitile() {
    return announceTitile;
  }

  public void setAnnounceTitile(String announceTitile) {
    this.announceTitile = announceTitile;
  }


  public String getAnnounceBody() {
    return announceBody;
  }

  public void setAnnounceBody(String announceBody) {
    this.announceBody = announceBody;
  }

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
  public Date getAnnounceTime() {
    return announceTime;
  }

  public void setAnnounceTime(Date announceTime) {
    this.announceTime = announceTime;
  }

}
