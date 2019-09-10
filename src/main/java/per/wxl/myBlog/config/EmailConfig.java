package per.wxl.myBlog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 22:45
 * @Description:
 */
@ConfigurationProperties(prefix = "email")
@Component
public class EmailConfig {
    private String prefix;
    private int expiredTime;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }
}
