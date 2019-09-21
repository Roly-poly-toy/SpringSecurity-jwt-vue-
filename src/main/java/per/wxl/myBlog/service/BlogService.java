package per.wxl.myBlog.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.wxl.myBlog.config.RedisConfig;
import per.wxl.myBlog.dao.BlogDao;
import per.wxl.myBlog.dao.TagDao;
import per.wxl.myBlog.dao.UserDao;
import per.wxl.myBlog.dao.UserViewsDao;
import per.wxl.myBlog.model.Blog;
import per.wxl.myBlog.model.Tag;
import per.wxl.myBlog.model.User;
import per.wxl.myBlog.model.UserViews;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Auther: wxl
 * @Date: 2019/9/14 23:35
 * @Description:
 */
@Service
@Transactional
public class BlogService {
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private UserViewsDao userViewsDao;
    @Autowired
    private RedisTemplate redisTemplate;


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
        for(int i=0;i<blogs.size();i++){
           Integer blogId=blogs.get(i).getBlogId();
            if(redisTemplate.hasKey(RedisConfig.BLOG_TOTAL_COUNT+blogId))
                blogs.get(i).setBlogViews((Integer) redisTemplate.opsForValue().get(RedisConfig.BLOG_TOTAL_COUNT+blogId));
        }

        return blogs;
    }

    public Blog getBlogById(Integer blogId,Integer userId) throws RuntimeException{
        Blog blog=blogDao.getBlogById(blogId);
        if(blog==null) throw new RuntimeException("博客不存在，或已被删除");

        if(!redisTemplate.hasKey(RedisConfig.USER_BLOG_VIEWS+userId)){
            UserViews userViews=userViewsDao.getUserViewsById(userId);
            if(userViews!=null){
                String blogIds=userViews.getBlogs();
                redisTemplate.opsForSet().add(RedisConfig.USER_BLOG_VIEWS+userId,
                        JSON.parseObject(blogIds, Set.class));
            }
        }

        //如果插入成功返回1，即该用户没有访问该博客 那么就让他的缓存访问量加一
        if(redisTemplate.opsForSet().add(RedisConfig.USER_BLOG_VIEWS+userId,blogId)==1){
            if(!redisTemplate.hasKey(RedisConfig.BLOG_TOTAL_COUNT+blogId))
                redisTemplate.opsForValue().set(RedisConfig.BLOG_TOTAL_COUNT+blogId,blog.getBlogViews());
            redisTemplate.opsForValue().increment(RedisConfig.BLOG_TOTAL_COUNT+blogId,1);
            blog.setBlogViews(blog.getBlogViews()+1);
        }




        return blog;
    }

}
