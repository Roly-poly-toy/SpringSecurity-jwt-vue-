package per.wxl.myBlog.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: wxl
 * @Date: 2019/9/17 22:26
 * @Description:
 */
public class String2DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=null;
        try {
            date=df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
