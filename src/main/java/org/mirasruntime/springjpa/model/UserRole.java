package org.mirasruntime.springjpa.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRole {
    ADMIN("Администратор"),
    MODER("Модератор"),
    USER("Пользователь");

    private final String viewName;
}
