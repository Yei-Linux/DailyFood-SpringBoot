package pe.yeilinux.dailyfood.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;
import pe.yeilinux.dailyfood.dto.UserDTO;
import pe.yeilinux.dailyfood.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdditionalInfoToken implements TokenEnhancer {
    @Autowired
    public UserService userService;

    @SneakyThrows
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDTO userDTO = this.userService.getUserInformation(authentication.getName());
        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo.put("user",userDTO.getUser());
        additionalInfo.put("email",authentication.getName());
        additionalInfo.put("userId",userDTO.getId());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);
        return jwtAccessTokenConverter.enhance(accessToken, authentication);
    }
}
