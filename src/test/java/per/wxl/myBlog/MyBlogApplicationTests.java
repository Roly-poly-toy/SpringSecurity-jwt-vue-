package per.wxl.myBlog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.wxl.myBlog.dao.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {
	@Autowired
	UserDao userDao;
	@Test
	public void contextLoads() {
		System.out.println(userDao);
		System.out.println(userDao.getUserByUsername("w"));
	}

}
