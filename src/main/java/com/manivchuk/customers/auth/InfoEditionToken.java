package com.manivchuk.customers.auth;

import com.manivchuk.customers.model.entity.User;
import com.manivchuk.customers.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Setter(onMethod_ = @Autowired)
public class InfoEditionToken implements TokenEnhancer {

    private UserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        User user = userService.findByUsername(oAuth2Authentication.getName());

        Map<String, Object> info = new HashMap<>();
        info.put("info_additional", "How are you!: ".concat(oAuth2Authentication.getName()));
        info.put("user_name", user.getUsername() + ": " + user.getUsername());
        info.put("firstName", user.getFirstName());
        info.put("lastName", user.getLastName());
        info.put("email", user.getEmail());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
