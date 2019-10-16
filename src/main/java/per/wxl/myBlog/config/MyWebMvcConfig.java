package per.wxl.myBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import per.wxl.myBlog.converter.String2DateConverter;

/**
 * @Auther: wxl
 * @Date: 2019/9/7 23:02
 * @Description:
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT")
                .exposedHeaders("newToken")
                .maxAge(3600);
    }
    @Bean
    public String2DateConverter string2DateConverter(){
        return new String2DateConverter();
    }
}
