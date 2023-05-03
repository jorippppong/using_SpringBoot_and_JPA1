package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")  //url : "http://localhost:8080/hello"
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";  //화면 이름 : template에서 hello.html 만들면 자동으로 여기에 붙는다.
    }
}
