package per.wxl.myBlog.model;

/**
 * @Auther: wxl
 * @Date: 2019/9/20 22:00
 * @Description:
 */
public class UserViews {
    private Integer userId;
    private String blogs;

    public UserViews(Integer userId, String blogs) {
        this.userId = userId;
        this.blogs = blogs;
    }

    public UserViews() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBlogs() {
        return blogs;
    }

    public void setBlogs(String blogs) {
        this.blogs = blogs;
    }
}
