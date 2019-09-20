package per.wxl.myBlog.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.wxl.myBlog.model.Blog;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.service.BlogService;
import per.wxl.myBlog.service.TagService;
import per.wxl.myBlog.utils.DataCheckUtil;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/14 23:35
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;



    @PostMapping("/sendBlog")
    public Result sendBlog(Integer userId,String title,String body,Integer[] tagsId){
        if(userId<=0||!DataCheckUtil.checkStringNull(title,body)||!DataCheckUtil.checkNotNegative(tagsId)){
            return new Result(201,"字段错误,请重新输入");
        }else if(!tagService.checkTagIsExist(tagsId)){
            return new Result(201,"服务器已更新，不存在此标签，请刷新");
        }else{
            Blog blog=blogService.sendBlog(userId,title,body,tagsId);
            return new Result(200,"发表成功",blog.getBlogId());
        }
    }

    @GetMapping("/getAllBlog")
    public Result getAllBlog(Date blogTime){

        List<Blog> blogs=blogService.getAllBlog(blogTime);
        return new Result(200,"查询成功",blogs);
    }

    @GetMapping("/getBlogById")
    public Result getBlogById(Integer blogId){
        if(blogId==null||!DataCheckUtil.checkNotNegative(blogId))
            return new Result(201,"博客Id不能为负数");
        Blog blog=blogService.getBlogById(blogId);
        return new Result(200,"查询博客成功",blog);
    }
}
