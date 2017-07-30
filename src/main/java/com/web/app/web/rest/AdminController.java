package com.web.app.web.rest;

import com.web.app.service.SocialService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final SocialService socialService;

    private final ProviderSignInUtils providerSignInUtils;

    public AdminController(SocialService socialService, ProviderSignInUtils providerSignInUtils) {
        this.socialService = socialService;
        this.providerSignInUtils = providerSignInUtils;
    }

    @GetMapping("/")
    public RedirectView signUp(WebRequest webRequest, @CookieValue(name = "NG_TRANSLATE_LANG_KEY", required = false, defaultValue = "\"en\"") String langKey) {
            
    	log.info("------------- Admin ------------------------");
            return new RedirectView(URIBuilder.fromUri("/admin.html")
                    .queryParam("success", "false")
                    .build().toString(), true);
    }
}
