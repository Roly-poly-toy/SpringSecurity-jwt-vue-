package per.wxl.myBlog.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

public class Blog {

  private Integer blogId;
  private String blogTitle;
  private String blogBody;
  private Integer blogDiscussCount;
  private Integer blogViews;
  private boolean blogState;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date blogTime;
  private Integer userId;  //方便插入
  private User user;
  private List<Tag> tags;

  public Blog() {
  }

  public Blog(String blogTitle, String blogBody, Integer blogDiscussCount, Integer blogViews, boolean blogState, Date blogTime, Integer userId) {
    this.blogTitle = blogTitle;
    this.blogBody = blogBody;
    this.blogDiscussCount = blogDiscussCount;
    this.blogViews = blogViews;
    this.blogState = blogState;
    this.blogTime = blogTime;
    this.userId = userId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Date getBlogTime() {
    return blogTime;
  }

  public void setBlogTime(Date blogTime) {
    this.blogTime = blogTime;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public Integer getBlogId() {
    return blogId;
  }

  public void setBlogId(Integer blogId) {
    this.blogId = blogId;
  }

  public String getBlogTitle() {
    return blogTitle;
  }

  public void setBlogTitle(String blogTitle) {
    this.blogTitle = blogTitle;
  }


  public String getBlogBody() {
    return blogBody;
  }

  public void setBlogBody(String blogBody) {
    this.blogBody = blogBody;
  }


  public Integer getBlogDiscussCount() {
    return blogDiscussCount;
  }

  public void setBlogDiscussCount(Integer blogDiscussCount) {
    this.blogDiscussCount = blogDiscussCount;
  }

  public Integer getBlogViews() {
    return blogViews;
  }

  public void setBlogViews(Integer blogViews) {
    this.blogViews = blogViews;
  }

  public boolean isBlogState() {
    return blogState;
  }

  public void setBlogState(boolean blogState) {
    this.blogState = blogState;
  }
}
