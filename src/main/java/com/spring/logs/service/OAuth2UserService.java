package com.spring.logs.service;

import com.spring.logs.model.User;
import com.spring.logs.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest) {

        OAuth2User oauthUser =
                super.loadUser(userRequest);

        String email =
                oauthUser.getAttribute("email");

        String name =
                oauthUser.getAttribute("name");

        userRepository.findByEmail(email)
                .orElseGet(() -> {

                    User user = User.builder()
                            .name(name)
                            .email(email)
                            .provider("GOOGLE")
                            .role("ROLE_USER")
                            .build();

                    return userRepository.save(user);
                });

        return oauthUser;
    }
}