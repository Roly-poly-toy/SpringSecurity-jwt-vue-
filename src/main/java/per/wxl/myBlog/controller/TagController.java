package per.wxl.myBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.model.Tag;
import per.wxl.myBlog.service.TagService;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/14 22:02
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/getAllTags")
    public Result getAllTags(){
        Result result=new Result(200,"成功");
        result.setData(tagService.getAllTags());
        return result;
    }
}
