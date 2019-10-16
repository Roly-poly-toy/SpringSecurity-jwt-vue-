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
    private Long expiredTime;
    private Long refreshTime;
    public static final String REDIS_TOKEN_KEY_PREFIX="TOKEN_";
    private String secret;
    private String prefix;
    private String header;

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime;
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
