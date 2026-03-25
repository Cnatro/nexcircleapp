package com.nexcircle.infrastructure.security.context;

import com.nexcircle.domain.user.service.SecurityContextService;
import com.nexcircle.infrastructure.security.user.UserDetails;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.exception.AppException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SpringSecurityContextAdapter implements SecurityContextService {


    @Override
    public UUID getCurrentUserId() {
        return getPrincipal().getId();
    }

    @Override
    public String getCurrentUserName() {
        // Thay vì dùng getName(), ta lấy trực tiếp từ UserDetails để đảm bảo nhất quán
        return getPrincipal().getUsername();
    }

    private UserDetails getPrincipal() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof UserDetails)
                .map(principal -> (UserDetails) principal)
                .orElseThrow(() -> new AppException(MessageCode.USER_NOT_FOUND));
    }
}
