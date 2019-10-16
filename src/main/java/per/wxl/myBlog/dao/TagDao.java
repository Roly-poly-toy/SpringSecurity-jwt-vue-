package per.wxl.myBlog.dao;

import per.wxl.myBlog.model.Tag;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/14 21:59
 * @Description:
 */
public interface TagDao {
    List<Tag> getAllTags();

    Tag getTagById(Integer tagId);

    List<Tag> getTagByBlogId(Integer blogId);

    void deleteTagByBlogId(Integer blogId);
}
