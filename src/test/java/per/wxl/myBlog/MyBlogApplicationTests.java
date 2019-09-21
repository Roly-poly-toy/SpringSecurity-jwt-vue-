package per.wxl.myBlog;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;
import per.wxl.myBlog.dao.BlogDao;
import per.wxl.myBlog.dao.UserDao;
import per.wxl.myBlog.dao.UserViewsDao;
import per.wxl.myBlog.model.Blog;
import per.wxl.myBlog.model.UserViews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {
	@Autowired
	UserDao userDao;
	@Autowired
	BlogDao blogDao;
	@Autowired
	UserViewsDao userViewsDao;
	@Test
	public void contextLoads() {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		Date date=new Date();
		System.out.println(date);
	}
	@Test
	public void test() throws ParseException {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =dateFormat.parse("2019-09-15 02:51:58");
		List<Blog> blogs=blogDao.getAllBlog(date);
		System.out.println(blogs);
	}
    @Test
	public void test1(){
	    Blog blog=blogDao.getBlogById(1);
        System.out.println(blog.getBlogTime());

    }
    @Test
    public void test2() throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =dateFormat.parse("2019-09-15 02:49:05");
        List<Blog> blogs=blogDao.getSomeBlog(date);
        System.out.println(blogs);
    }
    @Test
	public void test3(){
		UserViews userViews=userViewsDao.getUserViewsById(1);
		System.out.println(userViews);
	}

}
