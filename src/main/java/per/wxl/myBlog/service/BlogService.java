package per.wxl.myBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.wxl.myBlog.dao.BlogDao;
import per.wxl.myBlog.model.Blog;

import java.util.Date;

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

    @CachePut(key = "#blog.blogId")
    public Blog sendBlog(Integer userId, String title, String body, Integer[] tagsId) {
        Blog blog=new Blog(title,body,0,0,true,new Date(),userId);
        blogDao.saveBlog(blog);

        for(Integer tagId:tagsId){
            blogDao.saveBlogTag(blog.getBlogId(),tagId);
        }
        return blog;
    }
}
