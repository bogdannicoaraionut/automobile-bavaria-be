package com.automobilebavaria.backend.config.argumentresolver;

import com.automobilebavaria.backend.entity.User;
import com.automobilebavaria.backend.security.AuthenticatedUser;
import com.automobilebavaria.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Resolves the authenticated user and injects it into the controller method.
 * @see AuthenticatedUser
 */
@Component
@RequiredArgsConstructor
public class AuthenticatedUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticatedUser.class) &&
                parameter.getParameterType().equals(User.class);
    }

    @Override
    public User resolveArgument(@NonNull MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) {
        return userService.fetchAndUpdateUserWithAuthorities()
                .orElseThrow(() -> new AccessDeniedException("No authenticated user found."));
    }

}
