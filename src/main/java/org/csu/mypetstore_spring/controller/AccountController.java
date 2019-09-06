package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.Account;
import org.csu.mypetstore_spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/addNewAccount")
    public String addNewAccount(Account account,HttpSession session) {

        accountService.insertAccount(account);
        session.setAttribute("account",account);

        return "account/newAccountForm";
    }

    @RequestMapping("/saveAccount")
    public String saveAccount(Account account, HttpSession session){

        accountService.updateAccount(account);
        session.setAttribute("account",account);

        return "account/editAccountForm";
    }

    @RequestMapping("/viewAccount")
    public String viewAccount(){

        return "account/editAccountForm";
    }


    @RequestMapping("/template_menu")
    public String template_menu(String username,Model model){

        Account account = accountService.getAccount(username);
        model.addAttribute("account",account);

        return "common/template_menu";
    }

}
