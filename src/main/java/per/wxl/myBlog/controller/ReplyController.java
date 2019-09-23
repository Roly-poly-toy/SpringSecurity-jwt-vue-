package per.wxl.myBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.model.StatusCode;
import per.wxl.myBlog.service.ReplyService;
import per.wxl.myBlog.utils.DataCheckUtil;

/**
 * @Auther: wxl
 * @Date: 2019/9/22 22:19
 * @Description:
 */
@RestController
@RequestMapping("/reply")
@CrossOrigin
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/saveReply")
    public Result saveReply(Integer discussId,Integer userId,Integer replyId,String replyBody){
        if(!DataCheckUtil.checkNotNegative(discussId,userId)||!DataCheckUtil.checkStringNull(replyBody))
            return new Result(StatusCode.ERROR,"参数错误,请重新加载");
        try {
            replyService.saveReply(discussId,userId,replyId,replyBody);
        }catch (RuntimeException e){
            return new Result(StatusCode.ERROR,e.getMessage());
        }
        return new Result(StatusCode.OK,"回复发表成功");
    }

    @GetMapping("/getReplyByDiscussId")
    public Result getReplyByDiscussId(Integer discussId){
        if(!DataCheckUtil.checkNotNegative(discussId))
            return new Result(StatusCode.ERROR,"参数错误，请重新加载");
        return new Result(StatusCode.OK,"加载成功",replyService.getReplyByDiscussId(discussId));
    }
}
