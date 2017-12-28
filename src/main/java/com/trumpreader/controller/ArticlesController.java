package com.trumpreader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ArticlesController {

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String getArticles(Model model) {
        model.addAttribute("result", "Trump wins");
        return "articles";
    }
}
