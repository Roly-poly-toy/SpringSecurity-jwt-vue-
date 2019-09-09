package per.wxl.myBlog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: wxl
 * @Date: 2019/9/7 17:51
 * @Description:
 */
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtConfig {
    private Long time;
    public static final String REDIS_TOKEN_KEY_PREFIX="TOKEN_";
    private String secret;
    private String prefix;
    private String header;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
