package cn.edu.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {

    @RequestMapping(value = "/category/edit", method = RequestMethod.GET)
    public String edit() {
        return "edit";
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index() {
//        return "index";
//    }

    @RequestMapping("/")
    public String detail() {
        return "detail";
    }
}