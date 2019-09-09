package per.wxl.myBlog.model;


import java.util.Date;
import java.util.List;

public class Blog {

  private Integer blogId;
  private String blogTitle;
  private String blogBody;
  private long blogDiscussCount;
  private long blogViews;
  private boolean blogState;
  private Date blogTime;
  private User user;
  private List<Tag> tags;

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


  public long getBlogDiscussCount() {
    return blogDiscussCount;
  }

  public void setBlogDiscussCount(long blogDiscussCount) {
    this.blogDiscussCount = blogDiscussCount;
  }


  public long getBlogViews() {
    return blogViews;
  }

  public void setBlogViews(long blogViews) {
    this.blogViews = blogViews;
  }


  public boolean isBlogState() {
    return blogState;
  }

  public void setBlogState(boolean blogState) {
    this.blogState = blogState;
  }
}
