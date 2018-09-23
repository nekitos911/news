package com.application.news.domain.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum с ролями пользователей
 */
public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
