package per.wxl.myBlog.model;


public class Code {

  private String codeId;
  private Integer codeStatus;
  private User user;


  public String getCodeId() {
    return codeId;
  }

  public void setCodeId(String codeId) {
    this.codeId = codeId;
  }


  public Integer getCodeStatus() {
    return codeStatus;
  }

  public void setCodeStatus(Integer codeStatus) {
    this.codeStatus = codeStatus;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
