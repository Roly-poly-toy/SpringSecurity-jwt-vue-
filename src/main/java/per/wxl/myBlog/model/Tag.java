package per.wxl.myBlog.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


public class Tag {

  private Integer tagId;
  private String tagName;
  private List<Blog> blogs;

  public List<Blog> getBlogs() {
    return blogs;
  }

  public void setBlogs(List<Blog> blogs) {
    this.blogs = blogs;
  }

  public Integer getTagId() {
    return tagId;
  }

  public void setTagId(Integer tagId) {
    this.tagId = tagId;
  }

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

}
