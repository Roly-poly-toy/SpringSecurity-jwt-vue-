package per.wxl.myBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.wxl.myBlog.dao.BlogDao;
import per.wxl.myBlog.dao.DiscussDao;
import per.wxl.myBlog.dao.ReplyDao;
import per.wxl.myBlog.model.Discuss;
import per.wxl.myBlog.model.Reply;
import per.wxl.myBlog.model.User;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/22 22:19
 * @Description:
 */
@Service
@Transactional
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;
    @Autowired
    private DiscussDao discussDao;
    @Autowired
    private BlogDao blogDao;
    public void saveReply(Integer discussId,Integer userId,Integer replyId,String replyBody)throws RuntimeException {
        User user=new User();
        user.setUserId(userId);
        Discuss discuss=discussDao.getDiscussById(discussId);
        if(discuss==null) throw new RuntimeException("评论或已被删除");
        Reply reply=new Reply();
        reply.setReplyId(replyId);
        Reply reply1=new Reply();
        reply1.setReplyBody(replyBody);
        reply1.setReplyTime(new Date());
        reply1.setUser(user);
        reply1.setDiscuss(discuss);
        reply1.setReply(reply);
        replyDao.saveReply(reply1);
        blogDao.updateBlogDiscussCount(discuss.getBlog().getBlogId(),1);
        discussDao.updateReplyCount(discussId,1);
    }

    public List<Reply> getReplyByDiscussId(Integer discussId){
        return replyDao.getReplyByDiscussId(discussId);
    }
}
