package com.w2w.What2Watch.controllers;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.security.Principal;

import static org.springframework.security.oauth2.common.OAuth2AccessToken.ACCESS_TOKEN;

@RestController
public class LogoutController {

    @RequestMapping(value = "/exit")
    void RevokeAcess(Principal principal) {
        try {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost("https://oauth2.googleapis.com/revoke?token=" + oAuth2AuthenticationDetails.getTokenValue());
            org.apache.http.HttpResponse response = httpClient.execute(post);
            System.out.println(response);
        } catch (IOException e) {
        }
    }
}
