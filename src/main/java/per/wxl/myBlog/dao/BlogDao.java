package per.wxl.myBlog.dao;

import org.apache.ibatis.annotations.Param;
import per.wxl.myBlog.model.Blog;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wxl
 * @Date: 2019/9/14 23:35
 * @Description:
 */
public interface BlogDao {
    void saveBlog(Blog blog);

    void saveBlogTag(@Param("blogId") Integer blogId,@Param("tagId") Integer tagId);

    List<Blog> getAllBlog(Date blogTime);

    Blog getBlogById(Integer blogId);

    List<Blog> getSomeBlog(Date blogTime);

    void updateBlogViews(@Param("MyMap") Map<Integer,Integer> map);

    void updateBlogDiscussCount(@Param("blogId") Integer blogId, @Param("newVar") int i);

    void updateBlog(Blog blog);

    void updateBlogStatus(@Param("blogId") Integer blogId,@Param("i") int i);
}
