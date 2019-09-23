package per.wxl.myBlog.scheduleTask;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import per.wxl.myBlog.config.RedisConfig;
import per.wxl.myBlog.dao.BlogDao;
import per.wxl.myBlog.dao.UserViewsDao;
import per.wxl.myBlog.model.UserViews;

import java.util.*;

/**
 * @Auther: wxl
 * @Date: 2019/9/21 10:49
 * @Description:
 */
@Component
@Async
public class CollectTask {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserViewsDao userViewsDao;

    @Autowired
    private BlogDao blogDao;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void collectViews(){
        List<UserViews> list=new LinkedList<>();
        Set<String> keys=redisTemplate.keys(RedisConfig.USER_BLOG_VIEWS+"*");
        int start=RedisConfig.USER_BLOG_VIEWS.length();
        keys.forEach(key->{
            Set members = redisTemplate.opsForSet().members(key);
            String values=JSON.toJSONString(members);
            System.out.println(values);
            list.add(new UserViews(Integer.valueOf(key.substring(start)), values));
        });
        if(list.size()!=0) userViewsDao.saveUserViews(list);

        Map<Integer,Integer> map=new HashMap<>();
        Set<String> keys2=redisTemplate.keys(RedisConfig.BLOG_TOTAL_COUNT+"*");
        int start2=RedisConfig.BLOG_TOTAL_COUNT.length();
        keys2.forEach(key->{
            Integer count= (Integer) redisTemplate.opsForValue().get(key);
            map.put(Integer.valueOf(key.substring(start2)),count);
        });
        if(!map.isEmpty()) blogDao.updateBlogViews(map);

    }
}
