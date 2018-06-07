package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dispatcher {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
