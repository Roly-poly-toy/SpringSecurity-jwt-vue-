package per.wxl.myBlog.dao;

import per.wxl.myBlog.model.Reply;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/22 22:25
 * @Description:
 */
public interface ReplyDao {
    List<Reply> getReplyByDiscussId(Integer discussId);
    void saveReply(Reply reply);
    Reply getReplyById(Integer replyId);
}
