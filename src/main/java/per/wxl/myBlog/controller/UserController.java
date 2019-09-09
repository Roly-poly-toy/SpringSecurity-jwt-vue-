package per.wxl.myBlog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wxl
 * @Date: 2019/9/7 17:57
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Blog!";
    }


}
