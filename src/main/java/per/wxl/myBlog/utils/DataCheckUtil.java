package per.wxl.myBlog.utils;

import org.springframework.stereotype.Component;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 20:35
 * @Description:
 */

public class DataCheckUtil {
    public static boolean checkEmail(String email){
        String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
        return email.matches(regex);
    }

    public static boolean checkStringNull(String... strs){
        for(String s:strs){
            if(s==null||s.equals("")) return false;
        }
        return true;
    }

    public static boolean checkNotNegative(Integer... array){
       for(Integer i:array)
           if(i<=0) return false;
        return true;
    }

}
