package per.wxl.myBlog.dao;

import per.wxl.myBlog.model.Code;

/**
 * @Auther: wxl
 * @Date: 2019/9/10 13:55
 * @Description:
 */
public interface CodeDao {
    Code getCodeByCodeId(String codeId);

    void updateCode(Code code);
}
