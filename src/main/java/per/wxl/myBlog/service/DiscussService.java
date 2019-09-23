package per.wxl.myBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.wxl.myBlog.dao.BlogDao;
import per.wxl.myBlog.dao.DiscussDao;
import per.wxl.myBlog.dao.ReplyDao;
import per.wxl.myBlog.dao.UserDao;
import per.wxl.myBlog.model.Blog;
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
public class DiscussService {
    @Autowired
    private DiscussDao discussDao;
    @Autowired
    private ReplyDao replyDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BlogDao blogDao;
    public List<Discuss> getDiscussByBlogId(Integer blogId) {
        List<Discuss> list=discussDao.getDiscussByBlogId(blogId);
        for(Discuss discuss:list){
            List<Reply> replies=replyDao.getReplyByDiscussId(discuss.getDiscussId());
            for(Reply reply:replies){
                if(reply.getReply()!=null){
                    reply.setReply(replyDao.getReplyById(reply.getReply().getReplyId()));
                }
            }
            discuss.setReplies(replies);
        }
        return list;
    }


    public void saveDiscuss(Integer blogId, String discussBody, Integer userId) {
        Discuss discuss=new Discuss();
        User user=new User();
        user.setUserId(userId);
        Blog blog=new Blog();
        blog.setBlogId(blogId);
        discuss.setDiscussBody(discussBody);
        discuss.setDiscussTime(new Date());
        discuss.setUser(user);
        discuss.setBlog(blog);
        discuss.setDiscussReplyCount(0);
        discussDao.saveDiscuss(discuss);

        blogDao.updateBlogDiscussCount(blogId,1);
    }
}
