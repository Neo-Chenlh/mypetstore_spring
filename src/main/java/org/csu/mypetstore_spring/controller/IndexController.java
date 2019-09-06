package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.PublicKey;

@Controller
public class IndexController {

    @GetMapping("/viewIndex")
    public String viewIndex(Model model){

        return "index";
    }

    @GetMapping("/help")
    public String help(){
        return "help";
    }
}
