package per.wxl.myBlog.utils;

/**
 * @Auther: wxl
 * @Date: 2019/9/23 20:06
 * @Description:
 */
public class JsonUtil {
    public static Integer[] String2Array(String str){
        String[] strs=str.substring(1,str.length()-1).split("\\,");
        Integer[] res=new Integer[strs.length];
        for(int i=0;i<strs.length;i++)
            res[i]=Integer.valueOf(strs[i]);
        return res;
    }
}
