package per.wxl.myBlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import per.wxl.myBlog.dao.RoleDao;
import per.wxl.myBlog.dao.UserDao;

@SpringBootApplication
@MapperScan("per.wxl.myBlog.dao")
@EnableScheduling
@EnableAsync
public class MyBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBlogApplication.class, args);
	}

}
