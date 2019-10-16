package per.wxl.myBlog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import per.wxl.myBlog.model.Blog;
import per.wxl.myBlog.model.MyUserDetails;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.model.StatusCode;
import per.wxl.myBlog.service.BlogService;
import per.wxl.myBlog.service.TagService;
import per.wxl.myBlog.utils.DataCheckUtil;

import javax.servlet.http.HttpServletRequest;
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
            return new Result(StatusCode.ERROR,"字段错误,请重新输入");
        }else if(!tagService.checkTagIsExist(tagsId)){
            return new Result(StatusCode.ERROR,"服务器已更新，不存在此标签，请刷新");
        }else{
            blogService.sendBlog(userId,title,body,tagsId);
            return new Result(StatusCode.OK,"发表成功");
        }
    }
    @PutMapping("/editBlog")
    public Result editBlog(Integer blogId,Integer userId,String title,String body,Integer[] tagsId){
        if(blogId<0||userId<=0||!DataCheckUtil.checkStringNull(title,body)||!DataCheckUtil.checkNotNegative(tagsId)){
            return new Result(StatusCode.ERROR,"字段错误,请重新输入");
        }else if(!tagService.checkTagIsExist(tagsId)){
            return new Result(StatusCode.ERROR,"服务器已更新，不存在此标签，请刷新");
        }else{
           try {
               blogService.editBlog(blogId,userId,title,body,tagsId);
               return new Result(StatusCode.OK,"编辑成功");
           }catch(RuntimeException e){
               return new Result(StatusCode.ERROR,e.getMessage());
           }
        }
    }

    @GetMapping("/getAllBlog")
    public Result getAllBlog(Date blogTime){

        List<Blog> blogs=blogService.getAllBlog(blogTime);
        return new Result(StatusCode.OK,"查询成功",blogs);
    }

    @GetMapping("/getBlogById")
    public Result getBlogById(Integer blogId,Integer userId){
        if(blogId==null||!DataCheckUtil.checkNotNegative(blogId))
            return new Result(StatusCode.ERROR,"博客Id不能为负数");
        try {
            Blog blog=blogService.getBlogById(blogId,userId);
            return new Result(StatusCode.OK,"查询博客成功",blog);
        }catch (RuntimeException e){
            return new Result(StatusCode.ERROR,e.getMessage());
        }

    }
    @DeleteMapping("/deleteBlogById")
    public Result deleteBlogById(Integer blogId,Integer userId){
        if(!DataCheckUtil.checkNotNegative(blogId,userId))
            return new Result(StatusCode.ERROR,"参数错误");

        try {
            blogService.deleteBlogById(blogId,userId);
            return new Result(StatusCode.OK,"删除成功");
        }catch (RuntimeException e){
            return new Result(StatusCode.ERROR,e.getMessage());
        }

    }
}
