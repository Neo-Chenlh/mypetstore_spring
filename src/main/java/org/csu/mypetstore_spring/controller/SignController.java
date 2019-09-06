package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.Account;
import org.csu.mypetstore_spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SignController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/signOff")
    public String signOff(HttpSession session){

        session.setAttribute("account",null);

        return "catalog/main";
    }

    @RequestMapping("/signOn")
    public String signOn(String username, String password,HttpSession session){

        session.setAttribute("username",username);
        session.setAttribute("password",password);
        Account account = accountService.getAccount(username,password);
        session.setAttribute("account",account);

        if(account != null) {
            return "catalog/main";
        }
        else {

            String msg="Invalid username or password.  Signon failed";
            session.setAttribute("msg",msg);

            return "account/signOnForm";
        }
    }

    @RequestMapping("/viewSignOn")
    public String viewSignOn(){
            return "account/signOnForm";
    }

    @RequestMapping("/viewRegister")
    public String viewRegister(){
        return "account/newAccountForm";
    }


}
