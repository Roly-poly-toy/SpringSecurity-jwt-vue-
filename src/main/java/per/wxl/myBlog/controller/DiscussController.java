package per.wxl.myBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.wxl.myBlog.model.Discuss;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.service.DiscussService;
import per.wxl.myBlog.utils.DataCheckUtil;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/22 22:18
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/discuss")
public class DiscussController {
    @Autowired
    private DiscussService discussService;

    @GetMapping("/getDiscussByBlogId")
    public Result getDiscussByBlogId(Integer blogId){
        if(!DataCheckUtil.checkNotNegative(blogId)){
            return new Result(201,"参数错误，请重新加载");
        }
        List<Discuss> list=discussService.getDiscussByBlogId(blogId);
        return new Result(200,"加载成功",list);
    }
    @PostMapping("/saveDiscuss")
    public Result saveDiscuss(Integer blogId,String discussBody,Integer userId){
        if(!DataCheckUtil.checkNotNegative(blogId,userId)||!DataCheckUtil.checkStringNull(discussBody))
            return new Result(201,"参数错误，请重新加载");
        discussService.saveDiscuss(blogId,discussBody,userId);
        return new Result(200,"评论发表成功");
    }
}
