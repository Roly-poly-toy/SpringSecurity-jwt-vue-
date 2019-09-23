package per.wxl.myBlog.dao;

import org.apache.ibatis.annotations.Param;
import per.wxl.myBlog.model.Discuss;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/22 22:20
 * @Description:
 */
public interface DiscussDao {
    List<Discuss> getDiscussByBlogId(Integer blogId);
    void saveDiscuss(Discuss discuss);

    Discuss getDiscussById(Integer discussId);

    void updateReplyCount(@Param("discussId") Integer discussId,@Param("i") int i);
}
