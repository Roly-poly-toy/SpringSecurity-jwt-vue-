package per.wxl.myBlog.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 23:10
 * @Description:
 */
@RabbitListener(queues = "myBlog_Email")
@Component
public class EmailListener {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Value("${Email.expiredTime}")
    private int expiredTime;

    @RabbitHandler
    public void handlerMag(Map<String,String> map){
        String email=map.get("email");
        String code=map.get("code");
        sendEmail(email,code);
    }

    private void sendEmail(String email, String code) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(email);
        message.setSubject("myBlog_邮箱验证码");
        message.setText("邮箱验证码："+code+"，"+expiredTime+"分钟内有效");
    }

}
