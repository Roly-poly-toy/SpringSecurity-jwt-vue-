package per.wxl.myBlog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.wxl.myBlog.dao.BlogDao;
import per.wxl.myBlog.dao.TagDao;
import per.wxl.myBlog.dao.UserDao;
import per.wxl.myBlog.model.Blog;
import per.wxl.myBlog.model.Tag;
import per.wxl.myBlog.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/14 23:35
 * @Description:
 */
@Service
@Transactional
@CacheConfig(cacheNames = "Blog")
public class BlogService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TagDao tagDao;
    @CachePut(key = "#result.blogId")
    public Blog sendBlog(Integer userId, String title, String body, Integer[] tagsId) {
        Blog blog=new Blog(title,body,0,0,true,new Date(),userId);
        blogDao.saveBlog(blog);

        User user=userDao.getUserById(userId);
        blog.setUser(user);

        List<Tag> tags=new ArrayList<>();

        for(Integer tagId:tagsId){
            blogDao.saveBlogTag(blog.getBlogId(),tagId);
            tags.add(tagDao.getTagById(tagId));
        }

        blog.setTags(tags);
        return blog;
    }

    public List<Blog> getAllBlog(Date blogTime) {

        List<Blog> blogs=blogDao.getAllBlog(blogTime);

        return blogs;
    }
    @Cacheable
    public Blog getBlogById(Integer blogId) {
        Blog blog=blogDao.getBlogById(blogId);
        if(blog==null) throw new RuntimeException("博客不存在，或已被删除");
        return blog;
    }

}
