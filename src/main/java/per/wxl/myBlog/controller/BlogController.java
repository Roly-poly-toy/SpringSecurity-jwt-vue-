package per.wxl.myBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.service.BlogService;
import per.wxl.myBlog.service.TagService;
import per.wxl.myBlog.utils.DataCheckUtil;

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
            blogService.sendBlog(userId,title,body,tagsId);
            return new Result(200,"发表成功");
        }
    }
}
