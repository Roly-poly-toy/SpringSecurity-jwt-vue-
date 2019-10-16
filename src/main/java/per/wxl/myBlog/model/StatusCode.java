package per.wxl.myBlog.model;

/**
 * @Auther: wxl
 * @Date: 2019/9/16 17:14
 * @Description:
 */
public class StatusCode {
    public static final int OK=200; //成功
    public static final int ERROR=201; //一般失败
    public static final int USERNOTFOUNT=202;//不存在用户
    public static final int NOTMATCH=203;//用户密码不匹配
    public static final int DISABLED=204; //用户已被枷锁
    public static final int EXPIRED=205; //TOLEN过期
    public static final int REFRESHTOKENEXPIRED=206; //ResfreshTOLEN过期
    public static final int NOROLE=403;  //权限不足
    public static final int NOTFOUND=404; //找不到资源
    public static final int WEBERROR=500; //服务器内部错误
}
