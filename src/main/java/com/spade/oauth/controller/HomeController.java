package com.spade.oauth.controller;

import com.spade.oauth.redis.service.OauthStateService;
import com.spade.oauth.util.OauthStateUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    OauthStateService oauthStateService;
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        String state = OauthStateUtil.createState();

        oauthStateService.saveOauthState(state, "naver");
        model.addAttribute("responseType", "code");
        model.addAttribute("state", state);
        model.addAttribute("clientId", "UMKT4dIs6FAj23xkYaC1");
        model.addAttribute("redirectUri", "http://localhost:8080/test/callback");

        return "index";
    }
}
