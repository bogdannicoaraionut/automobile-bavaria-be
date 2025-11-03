package com.automobilebavaria.backend.config;

import com.automobilebavaria.backend.config.argumentresolver.AuthenticatedUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticatedUserArgumentResolver authenticatedUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticatedUserArgumentResolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200", "https://auto-bavaria-is2jhcdsl-bogdan-nicoaras-projects.vercel.app", "https://auto-bavaria-git-dev-bogdan-nicoaras-projects.vercel.app/?_vercel_share=gSHZIlm1GYqbXDAdQomCM41OvPjvfErA")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
