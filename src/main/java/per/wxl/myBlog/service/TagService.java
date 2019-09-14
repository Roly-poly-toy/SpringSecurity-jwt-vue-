package per.wxl.myBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import per.wxl.myBlog.dao.TagDao;
import per.wxl.myBlog.model.Tag;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/14 22:01
 * @Description:
 */
@Service
@CacheConfig(cacheNames = "Tag")
public class TagService {
    @Autowired
    private TagDao tagDao;

    @Cacheable(key ="methodName")
    public List<Tag> getAllTags(){
        return tagDao.getAllTags();
    }

    public boolean checkTagIsExist(Integer... tagsId) {
        for(Integer tagId:tagsId){
            if(tagDao.getTagById(tagId)==null)
                return false;
        }
        return true;
    }
}
