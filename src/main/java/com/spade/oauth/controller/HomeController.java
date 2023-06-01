package com.spade.oauth.controller;

import com.spade.oauth.util.OauthStateUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        String state = OauthStateUtil.createState();
        model.addAttribute("responseType", "code");
        model.addAttribute("state", state);
        model.addAttribute("clientId", "UMKT4dIs6FAj23xkYaC1");
        model.addAttribute("redirectUri", "http://localhost:8080/test/callback");

        session.setAttribute("state", state);
        return "index";
    }
}
