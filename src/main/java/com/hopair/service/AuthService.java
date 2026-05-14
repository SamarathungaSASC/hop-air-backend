package com.hopair.service;

import com.hopair.entity.User;
import com.hopair.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    /**
     * Resolves the current user from a JWT token.
     * On first login: matches by email and stores the Auth0 sub for future lookups.
     * On subsequent logins: looks up by auth0_sub directly.
     */
    @Transactional
    public User resolveUser(Jwt jwt) {
        String sub = jwt.getSubject();
        String email = jwt.getClaimAsString("https://hopair-api/email");

        return userRepository.findByAuth0Sub(sub)
            .orElseGet(() -> {
                if (email == null) {
                    throw new RuntimeException("Email claim missing from token. Check Auth0 Action is deployed.");
                }
                User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not provisioned: " + email));
                user.setAuth0Sub(sub);
                String name = jwt.getClaimAsString("https://hopair-api/name");
                if (name != null && user.getFullName() == null) {
                    user.setFullName(name);
                }
                return userRepository.save(user);
            });
    }
}
